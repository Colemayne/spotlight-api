package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.ReportItem;
import com.coleman.documenter.domain.reports.Reports;
import com.coleman.documenter.repository.group.GroupRepository;
import com.coleman.documenter.repository.reports.ReportItemRepository;
import com.coleman.documenter.repository.reports.ReportRepository;
import com.coleman.documenter.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ReportItemRepository reportItemRepository;
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
    public ReportItem addItem(ReportItem reportItem){
        Reports report = reportRepository.findById(reportItem.getReportId()).get();
        reportItem.setReport(report);
        return reportItemRepository.save(reportItem);
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
        for(ReportItem item: report.getReportItems()){

            reportItemRepository.save(item);
        }
        return reportRepository.save(report);
    }

}
