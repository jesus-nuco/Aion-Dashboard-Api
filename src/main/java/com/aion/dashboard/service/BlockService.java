package com.aion.dashboard.service;

import java.util.List;
import java.util.Optional;

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
import com.aion.dashboard.domainobject.BlockDO;
import com.aion.dashboard.domainobject.ParserState;
import com.aion.dashboard.domainobject.*;
import com.aion.dashboard.utility.RewardsCalculator;
import com.aion.dashboard.repository.BlockJpaRepository;
import com.aion.dashboard.repository.ParserStateJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SuppressWarnings("Duplicates")
 
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
