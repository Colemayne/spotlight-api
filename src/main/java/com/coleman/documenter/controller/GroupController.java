package com.coleman.documenter.controller;

import com.coleman.documenter.domain.businessResponses.TailoredGroups;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.group.GroupMembership;
import com.coleman.documenter.domain.group.GroupRequests;
import com.coleman.documenter.service.GroupService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/group/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private KeycloakRestTemplate restTemplate;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @RequestMapping(path = "/alive", method = RequestMethod.GET)
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok().body("Alive");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Group> addGroup(@RequestBody String groupName, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        System.out.println("hello??");
        return ResponseEntity.ok().body(groupService.createGroup(groupName, keycloakAuthenticationToken.getName()));
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable Integer id, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        boolean successful = groupService.deleteGroupById(keycloakAuthenticationToken.getName(), id);
        if(successful) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = {"/select","/select/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Group>> selectGroup(@PathVariable Optional<Integer> id, KeycloakAuthenticationToken keycloakAuthenticationToken){
        if (id.isPresent()) {
            return ResponseEntity.ok().body(groupService.findGroupById(id.get()));
        } else {
            return ResponseEntity.ok().body(groupService.findAllGroups());
        }
    }

    @RequestMapping(path = "/tailoredSelect", method = RequestMethod.GET)
    public ResponseEntity<TailoredGroups> selectTailoredGroups(KeycloakAuthenticationToken keycloakAuthenticationToken) {
        return ResponseEntity.ok().body(groupService.findTailoredGroups(keycloakAuthenticationToken.getName()));
    }

    @RequestMapping(path = "/showUsers", method = RequestMethod.GET)
    public ResponseEntity<List> findUsers(KeycloakAuthenticationToken keycloakAuthenticationToken) {
        return ResponseEntity.ok().body(restTemplate.getForEntity(URI.create(keycloakUrl+"/admin/realms/Spotlight/users"), List.class)
                .getBody());
    }

    @RequestMapping(path = "/addMembership", method = RequestMethod.POST)
    public ResponseEntity<GroupMembership> addMembership(@RequestBody GroupMembership groupMembership,
                                                         KeycloakAuthenticationToken keycloakAuthenticationToken) {
        return ResponseEntity.ok().body(groupService.addGroupMembership(keycloakAuthenticationToken.getName(), groupMembership));

    }

    @RequestMapping(path = "/deleteMembership/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteMembership(@PathVariable int id, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        try {
            return ResponseEntity.ok().body(groupService.removeGroupMembership(keycloakAuthenticationToken.getName(),id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/requestMembership/{id}", method = RequestMethod.POST)
    public ResponseEntity requestMembership(@PathVariable int id, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        try {
            GroupRequests groupRequest = new GroupRequests(keycloakAuthenticationToken.getName(), id);
            boolean response = groupService.addRequest(groupRequest, keycloakAuthenticationToken.getName());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = {"/requests","/requests/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<GroupRequests>> getRequests(@PathVariable Optional<Integer> id, KeycloakAuthenticationToken keycloakAuthenticationToken) {

        List<GroupRequests> groupRequests = new ArrayList<>();
        if (id.isPresent()) {
            groupRequests = groupService.findGroupRequestById(id.get());
        } else {
            List<GroupRequests> results = groupService.findAllGroupRequests();
            for(GroupRequests request: results) {
                if(request.getUsername().equals(keycloakAuthenticationToken.getName())){
                    groupRequests.add(request);
                }
            }
        }
        return ResponseEntity.ok().body(groupRequests);
    }

    @RequestMapping(path = "/accept/{id}", method = RequestMethod.POST)
    public ResponseEntity<GroupMembership> acceptRequest(@PathVariable int id, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        return ResponseEntity.ok().body(groupService.acceptRequest(id, keycloakAuthenticationToken.getName()));
    }

    @RequestMapping(path = "/deny/{id}", method = RequestMethod.POST)
    public ResponseEntity denyRequest(@PathVariable int id, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        try {
            groupService.denyRequest(id, keycloakAuthenticationToken.getName());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
