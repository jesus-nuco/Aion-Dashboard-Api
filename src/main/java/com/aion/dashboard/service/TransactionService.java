package com.aion.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.aion.dashboard.domainobject.TransactionDO;
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
import org.springframework.data.domain.Pageable;
import com.aion.dashboard.CacheConfig;
import com.aion.dashboard.domainobject.ParserState;
import com.aion.dashboard.repository.TransactionJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

 
public interface TransactionService {
	
	
	 
	public String getTransactionList(int pageNumber, int pageSize) throws Exception ;
	
	
	public String findByTransactionHash(String searchParam) throws Exception;
	
	Page<TransactionDO> findByIdBetween(Long startId, Long endId, Pageable pageable);

	TransactionDO getTransactionByTransactionHash(String transactionHash);

	Page<TransactionDO> findByIdBetweenAndFromAddrOrToAddr(Long startId, Long endId, String fromAddr, String toAddr,
			Pageable pageable);

	List<Object> getFromTransactionByAccountByIdBetween(Long startId, Long endId);

	List<Object> getToTransactionByAccountByIdBetween(Long startId, Long endId);
}
