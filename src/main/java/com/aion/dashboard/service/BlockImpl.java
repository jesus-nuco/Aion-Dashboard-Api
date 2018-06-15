package com.aion.dashboard.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

 
import com.aion.dashboard.domainobject.BlockDO;
import com.aion.dashboard.domainobject.ParserState;
import com.aion.dashboard.CacheConfig;
 import com.aion.dashboard.repository.BlockJpaRepository;
import com.aion.dashboard.repository.ParserStateJpaRepository;
import com.aion.dashboard.types.ParserStateType;
import com.aion.dashboard.utility.RewardsCalculator;
import com.aion.dashboard.utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class BlockImpl implements BlockService {
	
	@Autowired
	private ParserStateJpaRepository parserStateJpaRepository;
	
	@Autowired
	private BlockJpaRepository blockJpaRepository;


	@Override
	public Page<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber, Long endBlockNumber,
			String minerAddress, Pageable pageable) {
		
		return blockJpaRepository.findByBlockNumberBetweenAndMinerAddress(startBlockNumber, endBlockNumber, minerAddress, pageable);
	}

	@Override
	public Page<BlockDO> findByBlockNumberBetween(Long startBlockNumber, Long endBlockNumber, Pageable pageable) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findByBlockNumberBetween(startBlockNumber, endBlockNumber, pageable);
	}

	@Override
	public BlockDO findFirstByBlockNumber(Long blockNumber) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findByBlockNumber(blockNumber);
	}

	@Override
	public List<BlockDO> findByBlockNumberBetween(Long startBlockNumber, Long endBlockNumber) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findByBlockNumberBetween(startBlockNumber, endBlockNumber);
	}

	@Override
	public List<BlockDO> findByBlockTimestampBetween(Long startTime, Long endTime) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findByBlockTimestampBetween(startTime, endTime);
	}

	@Override
	public BlockDO findFirstByBlockHash(String blockHash) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findFirstByBlockHash(blockHash);
	}

	@Override
	public List<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber, Long endBlockNumber,
			String minerAddress) {
		// TODO Auto-generated method stub
		return blockJpaRepository.findByBlockNumberBetweenAndMinerAddress(startBlockNumber, endBlockNumber, minerAddress);
	}
	

	
	
	//@Cacheable(CacheConfig.BLOCK_LIST)
	public String getBlockList(int pageNumber, int pageSize) throws Exception {
		try {
			Optional<ParserState> blockParserState = parserStateJpaRepository.findById(ParserStateType.HEAD_BLOCK_TABLE.getId());
			JSONArray blockArray = new JSONArray();
			JSONObject blockObject = new JSONObject();
			Sort sort = new Sort(Sort.Direction.DESC, "blockNumber");
			
			if(blockParserState.isPresent()) {
			    long blockNumber = blockParserState.get().getBlockNumber();
			    //
				Page<BlockDO> blockPage = blockJpaRepository
                        .findByBlockNumberBetween(blockNumber-999L, blockNumber,
                                new PageRequest(pageNumber, pageSize, sort));
				List<BlockDO> blockList = blockPage.getContent();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

				if(blockList!=null && blockList.size()>0) {
					for(int i=0;i<blockList.size();i++) {
						BlockDO block = blockList.get(i);
						JSONObject result = new JSONObject(ow.writeValueAsString(block));
						result.remove("transactionList");
						result.put("blockReward", RewardsCalculator.calculateReward(block.getBlockNumber()));
						blockArray.put(result);
					}

					JSONObject pageObject = new JSONObject();
					pageObject.put("totalElements", blockPage.getTotalElements());
					pageObject.put("totalPages", blockPage.getTotalPages());
					pageObject.put("number", pageNumber);
					pageObject.put("size", pageSize);

					blockObject.put("content", blockArray).toString();
					blockObject.put("page", pageObject);
				}
			}

			if(blockArray.length()==0) {
				// empty content
				blockArray.put(new JSONObject().put("rel",  JSONObject.NULL));
				blockObject.put("content", blockArray).toString();
			}

			return blockObject.toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Cacheable(CacheConfig.BLOCK_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER)
	public String findByBlockNumberOrBlockHash(String searchParam) throws Exception{
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			JSONArray blockArray = new JSONArray();

			// find in block hash
			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");

			if(Utility.validHex(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.searchBlockHash(searchParam);
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(block));
					result.put("blockReward", RewardsCalculator.calculateReward(block.getBlockNumber()));
					result.remove("transactionList");
					blockArray.put(result);
					return new JSONObject().put("content", blockArray).toString();
				}
			}

			// find by block number
			else if(Utility.validLong(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.findByBlockNumber(Long.parseLong(searchParam));
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(block));
					result.put("blockReward", RewardsCalculator.calculateReward(block.getBlockNumber()));
					result.remove("transactionList");
					blockArray.put(result);
					return new JSONObject().put("content", blockArray).toString();
				}
			}

			// empty object if no result found
			blockArray.put(new JSONObject().put("rel", JSONObject.NULL));
			return new JSONObject().put("content", blockArray).toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Cacheable(CacheConfig.BLOCK_AND_TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER)
	public String getBlockAndTransactionDetailsFromBlockNumberOrBlockHash(String searchParam) throws Exception{
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			JSONArray blockArray = new JSONArray();

			// find in block hash
			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");

			if(Utility.validHex(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.searchBlockHash(searchParam);
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(block));
					result.put("blockReward", RewardsCalculator.calculateReward(block.getBlockNumber()));
					JSONArray transactionList = new JSONArray(result.get("transactionList").toString());
					result.put("transactionList", transactionList);
					blockArray.put(result);
					return new JSONObject().put("content", blockArray).toString();
				}
			}

			// find by block number
			else if(Utility.validLong(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.findByBlockNumber(Long.parseLong(searchParam));
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(block));
					result.put("blockReward", RewardsCalculator.calculateReward(block.getBlockNumber()));
					JSONArray transactionList = new JSONArray(result.get("transactionList").toString());
					result.put("transactionList", transactionList);
					blockArray.put(result);
					return new JSONObject().put("content", blockArray).toString();
				}
			}

			// empty object if no result found
			blockArray.put(new JSONObject().put("rel", JSONObject.NULL));
			return new JSONObject().put("content", blockArray).toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Cacheable(CacheConfig.TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER)
	public String findTransactionByBlockNumberOrBlockHash(String searchParam) throws Exception{
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			JSONArray blockArray = new JSONArray();

			// find in block hash
			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");

			if(Utility.validHex(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.searchBlockHash(searchParam);
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject blockContent = new JSONObject(ow.writeValueAsString(block));
					JSONArray transactionList = new JSONArray(blockContent.get("transactionList").toString());
					return new JSONObject().put("content", transactionList).toString();
				}
			}

			// find by block number
			else if(Utility.validLong(searchParam)) {
				// block master
				BlockDO block = blockJpaRepository.findByBlockNumber(Long.parseLong(searchParam));
				blockArray = new JSONArray();
				if(block!=null) {
					JSONObject blockContent = new JSONObject(ow.writeValueAsString(block));
					JSONArray transactionList = new JSONArray(blockContent.get("transactionList").toString());
					return new JSONObject().put("content", transactionList).toString();
				}
			}

			// empty object if no result found
			blockArray.put(new JSONObject().put("rel", JSONObject.NULL));
			return new JSONObject().put("content", blockArray).toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
