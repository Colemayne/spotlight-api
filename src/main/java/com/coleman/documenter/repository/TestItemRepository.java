package com.coleman.documenter.repository;

import com.coleman.documenter.domain.test.Test;
import com.coleman.documenter.domain.test.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestItemRepository extends CrudRepository<TestItem, Integer> {
    List<TestItem> findByTest(Test test);
}
