package com.coleman.documenter.controller;

import com.coleman.documenter.domain.group.Group;
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
@RequestMapping(value = "/api/group/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Group> addGroup(@RequestBody String groupName) {
        return ResponseEntity.ok().body(groupService.createGroup(groupName));
    }

    @RequestMapping(path = {"/select","/select/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Group>> selectGroup(@PathVariable Optional<Integer> id){
        if (id.isPresent()) {
            return ResponseEntity.ok().body(groupService.findGroupById(id.get()));
        } else {
            return ResponseEntity.ok().body(groupService.findAllGroups());
        }
    }



}
