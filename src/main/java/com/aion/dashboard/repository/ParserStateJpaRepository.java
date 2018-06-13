package com.aion.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aion.dashboard.domainobject.ParserState;

import java.util.Optional;

@Repository
public interface ParserStateJpaRepository extends PagingAndSortingRepository<ParserState, Integer> {
	@Override
	Optional<ParserState> findById(Integer id);
}
