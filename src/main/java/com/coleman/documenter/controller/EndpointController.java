package com.coleman.documenter.controller;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.application.endpoint.EndpointHeaders;
import com.coleman.documenter.domain.application.endpoint.EndpointParameters;
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

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/endpoint/v1")
public class EndpointController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Endpoint> addEndpoint(@RequestBody Endpoint sentEndpoint) {
        Endpoint endpoint = applicationService.addEndpoint(sentEndpoint);
        return ResponseEntity.ok().body(endpoint);
    }

    @RequestMapping(path = "/select/{id}", method = RequestMethod.GET)
    public ResponseEntity<Endpoint> selectEndpoint(@PathVariable Integer id){
            return ResponseEntity.ok().body(applicationService.findEndpointById(id));
    }

    @RequestMapping(path = "/select/group/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Endpoint>> selectEndpointByApplicationId(@PathVariable Integer id){
        Group group = groupService.findGroupById(id).get(0);
        Application application = applicationService.findApplicationById(id).get(0);
        return ResponseEntity.ok().body(applicationService.findEndpointByApplication(application));
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<Endpoint> saveEndpoint(@RequestBody Endpoint endpoint){
        return ResponseEntity.ok().body(applicationService.saveEndpoint(endpoint));
    }

    @RequestMapping(path = "/delete/parameter", method = RequestMethod.POST)
    public ResponseEntity deleteParameter(@RequestBody EndpointParameters endpointParameters) {
        applicationService.deleteParameter(endpointParameters.getId());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/delete/headers", method = RequestMethod.POST)
    public ResponseEntity deleteHeaders(@RequestBody EndpointHeaders endpointHeaders) {
        applicationService.deleteHeader(endpointHeaders.getId());
        return ResponseEntity.ok().build();
    }

}
