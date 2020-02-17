package com.coleman.documenter.repository.reports;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.Reports;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Reports, Integer> {
    List<Reports> findAll();
    List<Reports> findReportById(int id);
    List<Reports> findReportByGroup(Group group);
    Reports findById(int id);
}
