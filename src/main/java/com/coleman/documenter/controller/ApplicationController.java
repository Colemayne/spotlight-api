package com.coleman.documenter.controller;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.ApplicationEnvironment;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.service.ApplicationService;
import com.coleman.documenter.service.GroupService;
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
@RequestMapping(value = "/api/application/v1")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        applicationService.getPDF();
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Application> addApplication(@RequestBody Application sentApplication) {
        Application application = applicationService.addApplication(sentApplication);
        return ResponseEntity.ok().body(application);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<Application> saveApplication(@RequestBody Application sentApplication) {
        Application application = applicationService.saveApplication(sentApplication);
        return ResponseEntity.ok().body(application);
    }

    @RequestMapping(path = {"/select","/select/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Application>> selectApplication(@PathVariable Optional<Integer> id){
        if (id.isPresent()) {
            return ResponseEntity.ok().body(applicationService.findApplicationById(id.get()));
        } else {
            return ResponseEntity.ok().body(applicationService.findAllApplications());
        }
    }

    @RequestMapping(path = "/select/group/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Application>> selectApplicationByGroupId(@PathVariable Integer id){
        Group group = groupService.findGroupById(id).get(0);
        return ResponseEntity.ok().body(applicationService.findApplicationByGroup(group));
    }

    @RequestMapping(path = "/delete/environment", method = RequestMethod.POST)
    public ResponseEntity deleteEnvironment(@RequestBody ApplicationEnvironment applicationEnvironment) {
        applicationService.deleteEnvironment(applicationEnvironment.getId());
        return ResponseEntity.ok().build();
    }


}
