package com.aion.dashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aion.dashboard.domainobject.BlockDO;

 
 
public interface BlockService {
	
	Page<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress,Pageable pageable);
	Page<BlockDO> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber,Pageable pageable);
	BlockDO findFirstByBlockNumber(Long blockNumber);
	List<BlockDO> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber);
	List<BlockDO> findByBlockTimestampBetween(Long startTime,Long endTime);
	BlockDO findFirstByBlockHash(String blockHash);
	List<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress);

	String findTransactionByBlockNumberOrBlockHash(String searchParam) throws Exception;
	String getBlockAndTransactionDetailsFromBlockNumberOrBlockHash(String searchParam) throws Exception;
	String findByBlockNumberOrBlockHash(String searchParam) throws Exception;
	String getBlockList(int pageNumber, int pageSize) throws Exception;
}
