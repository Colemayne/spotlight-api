package com.coleman.documenter.service;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.ReportItem;
import com.coleman.documenter.domain.reports.Reports;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportService {
    Reports addReport(Reports report);
    ReportItem addItem(ReportItem reportItem);
    List<Reports> findAllReports();
    List<Reports> findReportById(int id);
    List<Reports> findReportByGroup(Group group);
    Reports saveReport(Reports report);
}
