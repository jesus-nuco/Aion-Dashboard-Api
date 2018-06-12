package com.aion.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.aion.dashboard.entities.ParserState;
import com.aion.dashboard.repository.ParserStateJpaRepository;
import com.aion.dashboard.types.ParserStateType;
import com.aion.dashboard.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.aion.dashboard.CacheConfig;
import com.aion.dashboard.entities.Transaction;
import com.aion.dashboard.repository.TransactionJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SuppressWarnings("Duplicates")
@Component
public class TransactionService {
	
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
				Page<Transaction> transactionPage = transactionJpaRepository
						.findByIdBetween(transactionId-999L, transactionId,
								new PageRequest(pageNumber, pageSize, sort));
				List<Transaction> transactionList = transactionPage.getContent();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

				if(transactionList!=null && transactionList.size()>0) {
					for(int i=0;i<transactionList.size();i++) {
						Transaction transaction = transactionList.get(i);
						JSONObject result = new JSONObject(ow.writeValueAsString(transaction));
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
				// transaction cache
				Transaction transaction = transactionJpaRepository.getTransactionByTransactionHash(searchParam);
				if(transaction!=null) {
					JSONObject result = new JSONObject(ow.writeValueAsString(transaction));
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
}
