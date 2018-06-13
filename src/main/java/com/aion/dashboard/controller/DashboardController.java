package com.aion.dashboard.controller;


import com.aion.dashboard.mapper.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/v2/dashboard")
public class DashboardController {


//    @Autowired
//    public Mapper mapper;
//    @Autowired
//    private BlockJpaRepository blockJpaRepository;
//
//    @Autowired
//    private TransactionJpaRepository transactionJpaRepository;
//
//    @Autowired
//    private BlockParserStateJpaRepository blockParserStateJpaRepository;
//
//    @Autowired
//    private TransactionParserStateJpaRepository transactionParserStateJpaRepository;
//
//    @Autowired
//    private SimpMessagingTemplate brokerMessagingTemplate;
//
//    @Autowired
//    private BlockService blockService;
//
//    @Autowired
//    private TransactionService transactionService;


//
//
//    // prometheous log
//    private static final Counter blockListCounter = Counter.build().name("blockListCounter").help("Number of requests for block list").register();
//    private static final Counter transactionListCounter = Counter.build().name("transactionListCounter").help("Number of requests for transaction list").register();
//    private static final Counter transactionDetailByTransactionHashCounter = Counter.build().name("transactionDetailByTransactionHashCounter").help("Number of requests for transaction detail by transaction hash").register();
//    private static final Counter blockDetailByBlockNumberOrBlockHashCounter = Counter.build().name("blockDetailByBlockNumberOrBlockHashCounter").help("Number of requests for block detail by block number or block hash").register();
//    private static final Counter blockAndTransactionDetailByBlockNumberOrBlockHashCounter = Counter.build().name("blockAndTransactionDetailByBlockNumberOrBlockHashCounter").help("Number of requests for block and transaction details for block number or block hash").register();
//    private static final Counter transactionDetailByBlockNumberOrBlockHashCounter = Counter.build().name("transactionDetailByBlockNumberOrBlockHashCounter").help("Number of requests for transaction detail by block number of block hash").register();
//    private static final Counter blockAndTransactionDetailsFromAddressCounter = Counter.build().name("blockAndTransactionDetailsFromAddressCounter").help("Number of requests for block-transaction details from address").register();
//    private static final Counter blockDetailsFromAddressCounter = Counter.build().name("blockDetailsFromAddressCounter").help("Number of requests for block details from address").register();
//    private static final Counter transactionDetailsFromAddressCounter = Counter.build().name("transactionDetailsFromAddressCounter").help("Number of requests for transaction details from address").register();
//    private static final Counter accountBalanceCounter = Counter.build().name("accountBalanceCounter").help("Number of requests for account balance").register();
//    private static final Counter accountStatisticCounter = Counter.build().name("accountStatisticCounter").help("Number of requests for account statistic").register();
//    private static final Counter dashboardCounter = Counter.build().name("dashboardCounter").help("Number of requests for dashboard").register();


    /**
     * Returns the list of blocks
     * @param page
     * @param size
     * @return
     */

    @RequestMapping(value = "/getBlockList", method = RequestMethod.GET)
    public ResponseEntity<Object> getBlockList(@RequestParam(value = "page", required=false)String page,
                                               @RequestParam(value = "size", required = false) String size){
      //  blockListCounter.inc();
        JSONObject object=null;
        try{


            if (!validInt(page) || !validInt(size)){
                throw new IllegalArgumentException();
            }
            else {
                // if there was no argument passed set default values
                int pageNumber = page == null ? 0 : Integer.parseInt(page);
                int pageSize = size == null ? 25 : Integer.parseInt(size);

                //object = mapper.getBlockList(pageNumber, pageSize);
            }
        }
        catch (IllegalArgumentException e){
            object = handleError("0x002");

        }
        catch (Exception e){
            object = handleError(e);
        }

        return packageAsEntity(object);
    }


    /**
     * Returns the list of transactions.
     * @param page
     * @param size
     * @return
     */

    @RequestMapping(value = "/getTransactionList", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionList(@RequestParam(value = "page", required=false)String page,
                                                     @RequestParam(value = "size", required = false) String size){
        JSONObject object=null;

        try {
            if (!validInt(page) || !validInt(size)){
                throw new IllegalArgumentException();
            }

            int pageNumber = page == null ? 0 : Integer.parseInt(page);
            int pageSize = size == null ? 25 : Integer.parseInt(size);

            //object = mapper.getTransactionList(pageNumber, pageSize);

        }
        catch (IllegalArgumentException e){
            object = handleError("0x002");
        }
        catch (Exception e){
            object = handleError(e);
        }

        return packageAsEntity(object);
    }



    @RequestMapping(value = "/findByTransactionHash", method = RequestMethod.GET)
    public ResponseEntity<Object> findByTrasactionHash(@RequestParam(value="searchParam",required=false) String searchParam){

        JSONObject object = new JSONObject();
        try {
            if (searchParam == null || searchParam.trim().isEmpty()){
                throw new IllegalArgumentException();
            }
            else {
                //object = mapper.findByTransactionHash(searchParam);
            }
        }
        catch (IllegalArgumentException e){
            object = handleError("0x001");
        }
        catch (Exception e){
            object = handleError(e);
        }

        return packageAsEntity(object);
    }




    static JSONObject handleError(Exception e){


        if (e != null) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();

        object.put("Message","Internal server error.");

        object.put("ErrorCode","0x201");
        return object;
    }

    static JSONObject handleError(String errorCode){

        JSONObject object = new JSONObject();
        switch (errorCode){
            case "0x001":
                object.put("Message", "Missing parameter in request.");
                break;
            case "0x002":
                object.put("Message","Malformed parameters.");
                break;
            default:
                object.put("Message", "Invalid Request.");
                break;
        }

        object.put("ErrorCode", errorCode);

        return object;
    }

    static ResponseEntity<Object> packageAsEntity(JSONObject object){

        if (object == null) {
            object = new JSONObject();
        }
        return packageAsEntity(200, object);


    }


    static ResponseEntity<Object> packageAsEntity(int statusCode, JSONObject object){
        if (object == null) {
            object = new JSONObject();
        }
        HttpStatus status;

        try{
            status = HttpStatus.valueOf(statusCode);
        }
        catch (Exception e){
            status = HttpStatus.OK;
            object = handleError(e);
        }
        return new ResponseEntity<>(object.toString(),status);
    }



    public boolean validLong(String searchParam) {
        try {
            if(searchParam.length()>18 || !searchParam.matches("-?[0-9]+"))
                return false;
            else
                return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean validInt(String searchParam) {
        try {
            if(searchParam.length()>18 || !searchParam.matches("-?[0-9]+"))
                return false;
            else
                return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean validHex(String searchParam) {
        if(searchParam.length()!=64 || !searchParam.matches("-?[0-9a-fA-F]+"))
            return false;
        else
            return true;
    }

}
