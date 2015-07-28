package com.hubson.lifegame.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hubson.lifegame.entity.BenchmarkEntry;

public interface BenchmarkRepository extends CrudRepository<BenchmarkEntry, Long> {
	List<BenchmarkEntry> findByElementsNumber(int elementsNumber);
}
