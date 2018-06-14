package com.aion.dashboard.controller;

import java.util.List;
import java.util.Optional;

import com.aion.dashboard.domainobject.*;
import com.aion.dashboard.types.ParserStateType;
import com.aion.dashboard.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.metrics.instrument.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aion.dashboard.blockchain.AionWeb3Api;
import com.aion.dashboard.domainobject.TransactionDO;
import com.aion.dashboard.CacheConfig;
import com.aion.dashboard.repository.BlockJpaRepository;
import com.aion.dashboard.repository.ParserStateJpaRepository;
import com.aion.dashboard.repository.TransactionJpaRepository;
import com.aion.dashboard.service.BlockService;
import com.aion.dashboard.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/dashboard")
public class Dashboard {

	@Autowired
	private BlockJpaRepository blockJpaRepository;

	@Autowired
	private TransactionJpaRepository transactionJpaRepository;

	@Autowired
	private ParserStateJpaRepository parserStateJpaRepository;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@Autowired
	private BlockService blockService;

	@Autowired
	private TransactionService transactionService;

	//String transactionHeader = "[transactionHash, fromAddr, toAddr, value, blockTimestamp, blockNumber]";
	//String blockHeader = "[blockHash, blockNumber, difficulty, nrgConsumed, nrgLimit, size, blockTimestamp, totalDifficulty, numTransactions]";

	static final int ACCOUNTS_TX_TO_SEARCH = 1000000-1;

	@RequestMapping(value = "/getBlockList", method = RequestMethod.GET)
	public String getBlockList(@RequestParam(value="page",required=false) String page,@RequestParam(value="size",required=false) String size) {
		try {
			int pageNumber = 0;
			int pageSize = 25;
			if(page!=null && Utility.validInt(page))
				pageNumber = Integer.parseInt(page);
			if(size!=null && Utility.validInt(size))
				pageSize = Integer.parseInt(size);
			return blockService.getBlockList(pageNumber, pageSize);
		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/getTransactionList", method = RequestMethod.GET)
	public String getTransactionList(@RequestParam(value="page",required=false) String page,@RequestParam(value="size",required=false) String size) {
		try {
			int pageNumber = 0;
			int pageSize = 25;
			if(page!=null && Utility.validInt(page))
				pageNumber = Integer.parseInt(page);
			if(size!=null && Utility.validInt(size))
				pageSize = Integer.parseInt(size);
			return transactionService.getTransactionList(pageNumber, pageSize);
		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/findByTransactionHash", method = RequestMethod.GET)
	public String findByTransactionHash(@RequestParam(value="searchParam",required=false) String searchParam) {
		try {
			if(searchParam != null) {
				if(searchParam.trim().isEmpty()) {
					return "{}";
				}else {
					return transactionService.findByTransactionHash(searchParam);
				}
			}else {
				return new JSONObject().put("result", "missing search param").toString();
			}
		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/findByBlockNumberOrBlockHash", method = RequestMethod.GET)
	public String findByBlockNumberOrBlockHash(@RequestParam(value="searchParam",required=false) String searchParam) {
		try {			
			if(searchParam != null) {
				if(searchParam.trim().isEmpty()) {
					return "{}";
				}else {
					return blockService.findByBlockNumberOrBlockHash(searchParam);
				}
			}else {
				return new JSONObject().put("result", "missing search param").toString();
			}

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/getBlockAndTransactionDetailsFromBlockNumberOrBlockHash", method = RequestMethod.GET)
	public String getBlockAndTransactionDetailsFromBlockNumberOrBlockHash(@RequestParam(value="searchParam",required=false) String searchParam) {
		try {			
			if(searchParam != null) {
				if(searchParam.trim().isEmpty()) {
					return "{}";
				}else {
					return blockService.getBlockAndTransactionDetailsFromBlockNumberOrBlockHash(searchParam);
				}
			}else {
				return new JSONObject().put("result", "missing search param").toString();
			}

		}catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/findTransactionByBlockNumberOrBlockHash", method = RequestMethod.GET)
	public String findTransactionByBlockNumberOrBlockHash(@RequestParam(value="searchParam",required=false) String searchParam) {
		try {			
			if(searchParam != null) {
				if(searchParam.trim().isEmpty()) {
					return "{}";
				}else {
					return blockService.findTransactionByBlockNumberOrBlockHash(searchParam);
				}
			}else {
				return new JSONObject().put("result", "missing search param").toString();
			}

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@Cacheable(CacheConfig.TRANSACTIONS_BY_ADDRESS)
	@RequestMapping(value = "/getTransactionsByAddress", method = RequestMethod.GET)
	public String getTransactionsByAddress(@RequestParam(value="searchParam",required=false) String searchParam,@RequestParam(value="transactionPage",required=false) String transactionPage,@RequestParam(value="transactionSize",required=false) String transactionSize) {
		try {
			int transactionPageNumber = 0;
			int transactionPageSize = 25;

			if(transactionPage!=null && Utility.validInt(transactionPage))
				transactionPageNumber = Integer.parseInt(transactionPage);

			if(transactionSize!=null && Utility.validInt(transactionSize))
				transactionPageSize = Integer.parseInt(transactionSize);

			Sort sort = new Sort(Sort.Direction.DESC, "blockNumber");

			if(searchParam==null)
				throw new Exception();

			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			JSONObject jsonObject = new JSONObject();

			JSONArray transactionArray = new JSONArray();
			JSONObject transactionObject = new JSONObject();

			// find by block hash
			if(Utility.validHex(searchParam)) {

				// transactions
				Optional<ParserState> parserState = parserStateJpaRepository.findById(ParserStateType.HEAD_BLOCK_TABLE.getId());
				if(parserState.isPresent()) {
					long transactionId = parserState.get().getTransactionId();
					Page<TransactionDO> transactionPageList = transactionJpaRepository.findByIdBetweenAndFromAddrOrToAddr(transactionId - ACCOUNTS_TX_TO_SEARCH,
							transactionId, searchParam, searchParam, new PageRequest(transactionPageNumber, transactionPageSize, sort));
					if(transactionPageList!=null) {
						List<TransactionDO> transactionDOList = transactionPageList.getContent();
						if(transactionDOList !=null && transactionDOList.size()>0) {
							for(int i = 0; i< transactionDOList.size(); i++) {
								JSONObject result = new JSONObject(ow.writeValueAsString(transactionDOList.get(i)));
								transactionArray.put(result);
							}

							JSONObject pageObject = new JSONObject();
							pageObject.put("totalElements", transactionPageList.getTotalElements());
							pageObject.put("totalPages", transactionPageList.getTotalPages());
							pageObject.put("number", transactionPageNumber);
							pageObject.put("size", transactionPageSize);

							transactionObject.put("content", transactionArray).toString();
							transactionObject.put("page", pageObject);

						}
					}
				}

			}

			if(transactionArray.length()==0) {
				transactionArray.put(new JSONObject().put("rel", JSONObject.NULL));
				transactionObject.put("content", transactionArray).toString();
			}

			return transactionObject.toString();

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}

	}

	@Cacheable(CacheConfig.BLOCKS_MINED_BY_ADDRESS)
	@RequestMapping(value = "/getBlocksMinedByAddress", method = RequestMethod.GET)
	public String getBlocksMinedByAddress(@RequestParam(value="searchParam",required=false) String searchParam,@RequestParam(value="blockPage",required=false) String blockPage,@RequestParam(value="blockSize",required=false) String blockSize) {
		try {
			int blockPageNumber = 0;
			int blockPageSize = 25;

			if(blockPage!=null && Utility.validInt(blockPage))
				blockPageNumber = Integer.parseInt(blockPage);

			if(blockSize!=null && Utility.validInt(blockSize))
				blockPageSize = Integer.parseInt(blockSize);

			Sort sort = new Sort(Sort.Direction.DESC, "blockNumber");

			if(searchParam==null)
				throw new Exception();

			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			JSONObject jsonObject = new JSONObject();

			JSONArray blockArray = new JSONArray();
			JSONObject blockObject = new JSONObject();

			// find by block hash
			if(Utility.validHex(searchParam)) {

				// blocks mined
				Optional<ParserState> blockParserState = parserStateJpaRepository.findById(ParserStateType.HEAD_BLOCK_TABLE.getId());
				if(blockParserState.isPresent()) {
					long blockNumber = blockParserState.get().getBlockNumber();
					Page<BlockDO> blockPageList = blockJpaRepository.findByBlockNumberBetweenAndMinerAddress(blockNumber - ACCOUNTS_TX_TO_SEARCH, blockNumber, searchParam, new PageRequest(blockPageNumber, blockPageSize, sort));
					if(blockPageList!=null) {
						List<BlockDO> blockList = blockPageList.getContent();
						if(blockList!=null && blockList.size()>0) {
							for(int i=0;i<blockList.size();i++) {
								JSONObject result = new JSONObject(ow.writeValueAsString(blockList.get(i)));
								result.remove("transactionList");
								blockArray.put(result);
							}

							JSONObject pageObject = new JSONObject();
							pageObject.put("totalElements", blockPageList.getTotalElements());
							pageObject.put("totalPages", blockPageList.getTotalPages());
							pageObject.put("number", blockPageNumber);
							pageObject.put("size", blockPageSize);

							blockObject.put("content", blockArray).toString();
							blockObject.put("page", pageObject);

						}
					}
				}
			}

			if(blockArray.length()==0) {
				blockArray.put(new JSONObject().put("rel", JSONObject.NULL));
				blockObject.put("content", blockArray).toString();
			}

			return blockObject.toString();

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}

	}

	@Cacheable(CacheConfig.ACCOUNT_DETAIL)
	@RequestMapping(value = "/getAccountDetails", method = RequestMethod.GET)
	public String getAccountDetails(@RequestParam(value="searchParam",required=false) String searchParam) {
		try {			
			if(searchParam != null) {
				if(searchParam.trim().isEmpty()) {
					return "{}";
				} else {
					// find in block hash
					if(searchParam.startsWith("0x"))
						searchParam = searchParam.replace("0x", "");

					JSONObject accountObject = new JSONObject();
					JSONArray accountArray = new JSONArray();

					if(Utility.validHex(searchParam)) {
						JSONObject response = AionWeb3Api.getInstance().ops_getAccountState(searchParam);
						accountObject.put("content", new JSONArray().put(response)).toString();
						return accountObject.toString();
					}else {
						accountArray.put(new JSONObject().put("rel",  JSONObject.NULL));
						accountObject.put("content", accountArray).toString();
						return accountObject.toString();
					}
				}
			} else {
				return new JSONObject().put("result", "missing search param").toString();
			}

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/getAccountStatistics", method = RequestMethod.GET)
	public String getAccountStatistics() {
		try {	
			DailyStatistics dailyStatistics = DailyStatistics.getInstance();

			JSONObject result = new JSONObject();

			result.put("miners", new JSONObject().put("content", dailyStatistics.getBlocksMined()));
			result.put("txnInbound", new JSONObject().put("content", dailyStatistics.getInboundTransactions()));
			result.put("txnOutbound", new JSONObject().put("content", dailyStatistics.getOutboundTransactions()));

			return result.toString();

		} catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			//result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewDashboard() {
		return RTStatistics.getInstance().getDashboardJSON();
	}

	@RequestMapping(value = "/newBlockReceived", method = RequestMethod.POST)
	public void receivedNewBlockDashboard() {
		this.brokerMessagingTemplate.convertAndSend("/dashboard/view", RTStatistics.getInstance().getDashboardJSON());
	}
}



