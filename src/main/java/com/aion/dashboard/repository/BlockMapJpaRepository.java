package com.aion.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aion.dashboard.domainobject.BlockMap;

@Repository
public interface BlockMapJpaRepository extends PagingAndSortingRepository<BlockMap, String> {
}
