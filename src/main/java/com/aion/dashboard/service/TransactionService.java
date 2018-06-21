package com.aion.dashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aion.dashboard.domainobject.TransactionDO;

 
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
