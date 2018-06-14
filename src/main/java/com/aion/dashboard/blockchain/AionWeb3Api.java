package com.aion.dashboard.blockchain;

import com.aion.dashboard.config.Config;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Metrics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

//import static org.apache.log4j.helpers.LogLog.debug;

public class AionWeb3Api
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    HttpHeaders headers;
    RestTemplate restTemplate;
    JSONArray epList = new JSONArray();
    String ep;

    static final int HB_FAIL_THRESHOLD = 3; // number of failed heartbeats before switching out api endpoints
    static final int HB_INTERVAL_MS = 5_000;
    AtomicLong failcount = new AtomicLong(0);

    static final int HTTP_TIMEOUT_MS_CONNECT = 5_000; // 5s
    static final int HTTP_TIMEOUT_MS_READ = 10_000; // 10s

    private AionWeb3Api() {
        epList = Config.getConfig().getWeb3ApiConnectionList();
        ep = epList.optString(0, null);

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

        /*
        The connection timeout is the timeout in making the initial connection; i.e. completing the TCP connection handshake.
        The read timeout is the timeout on waiting to read data.
        Specifically, if the server fails to send a byte <timeout> seconds after the last byte, a read timeout error will be raised.*/
        restTemplate = restTemplateBuilder
                    .setConnectTimeout(HTTP_TIMEOUT_MS_CONNECT)
                    .setReadTimeout(HTTP_TIMEOUT_MS_READ)
                    .build();

        // must have at-least one endpoint in list
        if (epList.length() > 0)
            new Thread(() -> this.threadWeb3Heartbeat(), "web3-heartbeat").start();
    }

    private void threadWeb3Heartbeat() {
        while(true) {
            try {
                if (failcount.get() >= HB_FAIL_THRESHOLD) {
                    failcount.set(0L);

                    // try to failover to the best available connection on list
                    for (int i = 0; i < epList.length(); i++) {
                        String _ep = epList.optString(i, null);
                        if (_ep == null) continue;

                        // if we can get a successful ping, swap out the connection with this one.
                        try {
                            String pong = (String) call(_ep, "ping", null);
                            if (pong.equalsIgnoreCase("pong")) {
                                ep = _ep;
                                log.debug("web3-hearbeat: failing-over to endpoint: "+ep);
                                break;
                            }
                        } catch (Exception e) {
                            // ping failed, ping the next connection on the list.
                            continue;
                        }
                    }
                }

                try {
                    String pong = ping();
                    if (!pong.equalsIgnoreCase("pong")) {
                        throw new Exception();
                    }
                    failcount.set(0L); // we're OK
                } catch (Exception e) {
                    Metrics.counter("aion.web3.failcount").increment();
                    long fc = failcount.incrementAndGet();
                    log.debug("web3-hearbeat: failure. count="+fc);
                }

                Thread.sleep(HB_INTERVAL_MS);
            }
            catch (InterruptedException interrupted) {
                log.error("threadWeb3Heartbeat() interrupted ... breaking.", interrupted);
                break;
            }
            catch (Exception f) {
                log.error("Unexpected exception in threadWeb3Heartbeat()", f);
            }
        }
    }

    private static class AionWeb3ApiHolder {
        static final AionWeb3Api INSTANCE = new AionWeb3Api();
    }

    public static AionWeb3Api getInstance() {
        return AionWeb3Api.AionWeb3ApiHolder.INSTANCE;
    }

    public class Web3ErrorException extends Exception { }

    private Object call(String endpoint, String method, JSONArray params) throws Web3ErrorException {
        if (ep == null) {
            log.debug("web3-api: invaid ep provided "+endpoint);
            throw new Web3ErrorException();
        }

        // create request body
        JSONObject request = new JSONObject();
        try {
			request.put("method", method);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (params != null && params.length() > 0)
			try {
				request.put("params", params);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        Object result = null;
        boolean error = false;
        try {
            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

            ResponseEntity<String> r = restTemplate
                    .exchange(endpoint, HttpMethod.POST, entity, String.class);
            log.trace("web3-api: rpc-call "+method);

            if (r.getStatusCode() == HttpStatus.OK) {
                JSONObject json = new JSONObject(r.getBody());
                result = json.opt("result");
                if (result == null) {
                    error = true;
                    log.error("web3-api: json rpc call errored out: " + json.toString());
                }
            } else {
                error = true;
                log.error("web3-api: non 200 status code");
            }
        } catch (Exception e) {
            error = true;
            log.error("web3-api: exception thrown by rpc call", e);
        }

        if (error) throw new Web3ErrorException();

        return result;
    }

    public JSONObject ops_getChainHeadView() throws Web3ErrorException {
        return (JSONObject)call(ep,"ops_getChainHeadView", null);
    }

    public long ops_getChainHeadViewBestBlock() throws Web3ErrorException {
        String n = call(ep,"ops_getChainHeadViewBestBlock", null) + "";
        Long v = Long.parseLong(n);
        return v;
    }

    public String ping() throws Web3ErrorException {
        return (String)call(ep,"ping", null);
    }

    public Long eth_blockNumber() throws Web3ErrorException {
        String n = call(ep,"eth_blockNumber", null) + "";
        Long v = Long.parseLong(n);
        return v;
    }

    public JSONObject ops_getAccountState(String address) throws Web3ErrorException {
        return (JSONObject)call(ep,"ops_getAccountState", new JSONArray().put(address));
    }
}