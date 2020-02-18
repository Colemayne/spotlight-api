package com.coleman.documenter.controller;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.reports.ReportTest;
import com.coleman.documenter.domain.reports.Reports;
import com.coleman.documenter.domain.test.Test;
import com.coleman.documenter.domain.test.TestItem;
import com.coleman.documenter.repository.TestItemRepository;
import com.coleman.documenter.repository.TestRepository;
import com.coleman.documenter.service.GroupService;
import com.coleman.documenter.service.ReportService;
import com.coleman.documenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping(value = "/api/test/v1")
public class TestController {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestItemRepository testItemRepository;
    @Autowired
    private TestService testService;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Test> addTests(@RequestBody Test test) {
        System.out.println("hello");
        return ResponseEntity.ok().body(testRepository.save(test));
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Test>> findAllTests() { return ResponseEntity.ok().body(testRepository.findAll()); }


    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteTest(@PathVariable Integer id) {
        boolean result = testService.deleteById(id);
        System.out.println(result);
        //testRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
