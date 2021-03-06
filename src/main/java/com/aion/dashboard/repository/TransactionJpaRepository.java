package com.aion.dashboard.repository;

import java.util.List;

import com.aion.dashboard.domainobject.TransactionDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJpaRepository extends PagingAndSortingRepository<TransactionDO,Long> {
	Page<TransactionDO> findByIdBetween(Long startId, Long endId, Pageable pageable);
	@Query(value = "select * from transaction where id = (select id from transaction_map where transaction_hash = ?1)", nativeQuery = true)
    TransactionDO getTransactionByTransactionHash(String transactionHash);
	Page<TransactionDO> findByIdBetweenAndFromAddrOrToAddr(Long startId, Long endId, String fromAddr, String toAddr, Pageable pageable);
	@Query(value = "select from_addr, avg(nrg_price), avg(nrg_consumed), count(*) as count from transaction where id between ?1 and ?2 group by from_addr order by count desc limit 250;", nativeQuery = true)
	List<Object> getFromTransactionByAccountByIdBetween(Long startId, Long endId);
	@Query(value = "select to_addr, avg(nrg_price), avg(nrg_consumed), count(*) as count from transaction where id between ?1 and ?2 group by to_addr order by count desc limit 250;", nativeQuery = true)
	List<Object> getToTransactionByAccountByIdBetween(Long startId, Long endId);
}