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

import com.aion.dashboard.CacheConfig;
import com.aion.dashboard.domainobject.ParserState;
import com.aion.dashboard.domainobject.TransactionDO;
import com.aion.dashboard.repository.ParserStateJpaRepository;
import com.aion.dashboard.repository.TransactionJpaRepository;
import com.aion.dashboard.types.ParserStateType;
import com.aion.dashboard.utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class TransactionImpl implements TransactionService {
	                                        
	 	@Autowired
	private ParserStateJpaRepository parserStateJpaRepository;
	
	@Autowired
	private TransactionJpaRepository transactionJpaRepository;
	

	@Cacheable(CacheConfig.TRANSACTION_LIST)
	public String getTransactionList(int pageNumber, int pageSize) throws Exception {
		try {
			Optional<ParserState> parserState = parserStateJpaRepository.findById(ParserStateType.HEAD_BLOCK_TABLE.getId());
			JSONArray transactionArray = new JSONArray();
			JSONObject transactionObject = new JSONObject();
			Sort sort = new Sort(Sort.Direction.DESC, "id");
			
			if(parserState.isPresent()) {
				long transactionId = parserState.get().getTransactionId();
				Page<TransactionDO> transactionPage = transactionJpaRepository
						.findByIdBetween(transactionId-999L, transactionId,
								new PageRequest(pageNumber, pageSize, sort));
				List<TransactionDO> transactionDOList = transactionPage.getContent();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

				if(transactionDOList !=null && transactionDOList.size()>0) {
					for(int i = 0; i< transactionDOList.size(); i++) {
						TransactionDO transactionDO = transactionDOList.get(i);
						JSONObject result = new JSONObject(ow.writeValueAsString(transactionDO));
						transactionArray.put(result);
					}

					JSONObject pageObject = new JSONObject();
					pageObject.put("totalElements", transactionPage.getTotalElements());
					pageObject.put("totalPages", transactionPage.getTotalPages());
					pageObject.put("number", pageNumber);
					pageObject.put("size", pageSize);

					transactionObject.put("content", transactionArray).toString();
					transactionObject.put("page", pageObject);
				}
			}

			if(transactionArray.length()==0) {
				// empty content
				transactionArray.put(new JSONObject().put("rel",  JSONObject.NULL));
				transactionObject.put("content", transactionArray).toString();
			}

			return transactionObject.toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Cacheable(CacheConfig.TRANSACTION_DETAIL_FROM_TRANSACTION_HASH)
	public String findByTransactionHash(String searchParam) throws Exception{
		try {
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			JSONArray transactionArray = new JSONArray();

			// find in block hash
			if(searchParam.startsWith("0x"))
				searchParam = searchParam.replace("0x", "");
			if(Utility.validHex(searchParam)) {
				// transactionDO cache
				TransactionDO transactionDO = transactionJpaRepository.getTransactionByTransactionHash(searchParam);
				if(transactionDO !=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(transactionDO));
					transactionArray.put(result);
					return new JSONObject().put("content", transactionArray).toString();
				}
			}

			// empty object if no result found
			transactionArray.put(new JSONObject().put("rel", JSONObject.NULL));
			return new JSONObject().put("content", transactionArray).toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	@Override 
	public Page<TransactionDO> findByIdBetween(Long startId,Long endId,Pageable pageable){
		return  transactionJpaRepository.findByIdBetween(startId, endId, pageable);
	}
	@Override 
	public TransactionDO getTransactionByTransactionHash(String transactionHash) {
		return transactionJpaRepository.getTransactionByTransactionHash(transactionHash);
	}
	@Override 
	public Page<TransactionDO> findByIdBetweenAndFromAddrOrToAddr(Long startId,Long endId,String fromAddr, String toAddr, Pageable pageable){
		return transactionJpaRepository.findByIdBetweenAndFromAddrOrToAddr(startId, endId, fromAddr, toAddr, pageable);
	}
	@Override 
	public List<Object> getFromTransactionByAccountByIdBetween(Long startId, Long endId){
		return transactionJpaRepository.getFromTransactionByAccountByIdBetween(startId, endId);
	}
	@Override 
	public List<Object> getToTransactionByAccountByIdBetween(Long startId, Long endId){
		return transactionJpaRepository.getFromTransactionByAccountByIdBetween(startId, endId);
	}
	
	public boolean validHex(String searchParam) {
		if(searchParam.length()!=64 || !searchParam.matches("-?[0-9a-fA-F]+"))
			return false;
		else
			return true;
	}



}
