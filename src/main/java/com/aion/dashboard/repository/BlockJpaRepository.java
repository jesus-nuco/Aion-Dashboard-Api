package com.aion.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aion.dashboard.domainobject.BlockDO;

@Repository
public interface BlockJpaRepository extends PagingAndSortingRepository<BlockDO, Long> {
	@Query(value = "select block_number,block_timestamp from block where block_number >= ?1 and block_number <= ?2 order by block_number desc", nativeQuery = true)
	List<Object> getTimestampBetween(long start,long end);
	@Query(value = "select sum(num_transactions),max(num_transactions) from block where block_number >= ?1 and block_number <= ?2", nativeQuery = true)
	List<Object> getSumAndMaxTransactionsInBlockRange(long start,long end);
	@Query(value = "select * from block where block_number = (select block_number from block_map where block_hash = ?1)", nativeQuery = true)
	BlockDO searchBlockHash(String blockHash);
	@Query(value = "select miner_address, avg(num_transactions), count(*) as count from block where block_number between ?1 and ?2 group by miner_address order by count desc limit 250;", nativeQuery = true)
	List<Object> getBlocksMinedByAccountByBlockNumberBetween(Long startBlockNumber, Long endBlockNumber);
	Page<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress,Pageable pageable);
	Page<BlockDO> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber,Pageable pageable);
	BlockDO findByBlockNumber(Long blockNumber);

	List<BlockDO> findByBlockTimestampBetween(Long startTime,Long endTime);
	BlockDO findFirstByBlockHash(String blockHash);
	List<BlockDO> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress); 
	List<BlockDO> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber);

}

/*
 @Query(value = "select sum(num_transactions),max(num_transactions) from block where block_number >= ?1 and block_number <= ?2", nativeQuery = true)
	List<Object> getSumAndMaxTransactionsInBlockRange(long start,long end);
	@Query(value = "select miner_address, avg(num_transactions), count(*) as count from block where block_number between ?1 and ?2 group by miner_address order by count desc limit 250;", nativeQuery = true)
	List<Object> getBlocksMinedByAccountByBlockNumberBetween(Long startBlockNumber, Long endBlockNumber);
	Page<Block> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress,Pageable pageable);
	Page<Block> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber,Pageable pageable);
	Block findByBlockNumber(Long blockNumber);
	
	//Updated from old native query
	@Query(value = "select block_number,block_timestamp from block where block_number >= ?1 and block_number <= ?2 order by block_number desc", nativeQuery = true)
	List<Object> getTimestampBetween(long start,long end);
	@Query(value = "select * from block where block_number = (select block_number from block_map where block_hash = ?1)", nativeQuery = true)
	Block searchBlockHash(String blockHash);
	
	
	
	
	//Recent by Jesus
	List<Block> findByBlockNumberBetween(Long startBlockNumber,Long endBlockNumber);
	
	Block findFirstByBlockHash(String blockHash);
	List<Block> findByBlockNumberBetweenAndMinerAddress(Long startBlockNumber,Long endBlockNumber,String minerAddress);*/
