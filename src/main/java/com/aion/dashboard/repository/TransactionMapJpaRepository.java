package com.aion.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aion.dashboard.domainobject.TransactionMap;

@Repository
public interface TransactionMapJpaRepository extends PagingAndSortingRepository<TransactionMap, String> {
}