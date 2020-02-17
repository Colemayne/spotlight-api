package com.coleman.documenter.controller;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.ReportItem;
import com.coleman.documenter.domain.reports.Reports;
import com.coleman.documenter.service.GroupService;
import com.coleman.documenter.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/report/v1")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Reports> addApplication(@RequestBody Reports sentReport) {
        Reports report = reportService.addReport(sentReport);
        return ResponseEntity.ok().body(report);
    }

    @RequestMapping(path = "/addItem", method = RequestMethod.POST)
    public ResponseEntity<ReportItem> addItem(@RequestBody ReportItem reportItem) {
        return ResponseEntity.ok().body(reportService.addItem(reportItem));
    }

    @RequestMapping(path = {"/select","/select/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Reports>> selectApplication(@PathVariable Optional<Integer> id){
        if (id.isPresent()) {
            return ResponseEntity.ok().body(reportService.findReportById(id.get()));
        } else {
            return ResponseEntity.ok().body(reportService.findAllReports());
        }
    }

    @RequestMapping(path = "/select/group/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Reports>> selectReportByGroupId(@PathVariable Integer id){
        Group group = groupService.findGroupById(id).get(0);
        return ResponseEntity.ok().body(reportService.findReportByGroup(group));
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<Reports> saveApplication(@RequestBody Reports sentReport) {
        Reports report = reportService.saveReport(sentReport);
        return ResponseEntity.ok().body(report);
    }

}
