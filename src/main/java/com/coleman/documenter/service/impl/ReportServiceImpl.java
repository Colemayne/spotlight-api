package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.ReportItem;
import com.coleman.documenter.domain.reports.ReportTest;
import com.coleman.documenter.domain.reports.Reports;
import com.coleman.documenter.repository.group.GroupRepository;
import com.coleman.documenter.repository.reports.ReportItemRepository;
import com.coleman.documenter.repository.reports.ReportRepository;
import com.coleman.documenter.repository.reports.ReportTestRepository;
import com.coleman.documenter.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ReportItemRepository reportItemRepository;
    @Autowired
    ReportTestRepository reportTestRepository;
    @Autowired
    GroupRepository groupRepository;

    @Override
    public Reports addReport(Reports report){
        LocalDateTime localDateTime = LocalDateTime.now();
        Group group = groupRepository.findGroupById(report.getGroupId()).get(0);
        report.setGroup(group);
        report.setReportDate(localDateTime);
        report.setReportLastUpdated(localDateTime);
        return reportRepository.save(report);
    }

    @Override
    public ReportTest addItem(ReportTest reportTest){
        Reports report = reportRepository.findById(reportTest.getRid()).get();
        reportTest.setReport(report);
        return reportTestRepository.save(reportTest);
    }

    @Override
    public List<Reports> findAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<Reports> findReportById(int id){
        return reportRepository.findReportById(id);
    }

    @Override
    public List<Reports> findReportByGroup(Group group) {
        return reportRepository.findReportByGroup(group);
    }

    @Override
    public Reports saveReport(Reports report) {
        Group group = groupRepository.findGroupById(report.getGroupId()).get(0);
        report.setGroup(group);
        for(ReportTest item: report.getReportTests()){
            item.setReport(report);
            reportTestRepository.save(item);
        }
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public void deleteItemById(Integer id) {
        reportTestRepository.deleteById(id);
    }

}
