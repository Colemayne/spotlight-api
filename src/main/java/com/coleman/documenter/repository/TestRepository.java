package com.coleman.documenter.repository;

import com.coleman.documenter.domain.reports.ReportTest;
import com.coleman.documenter.domain.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Integer> {
}
