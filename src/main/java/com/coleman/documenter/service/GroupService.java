package com.coleman.documenter.service;

import com.coleman.documenter.domain.businessResponses.TailoredGroups;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.group.GroupMembership;
import com.coleman.documenter.domain.group.GroupRequests;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupService {
    Group createGroup(String groupName, String username);
    List<Group> findGroupById(int id);
    List<Group> findAllGroups();
    TailoredGroups findTailoredGroups(String username);
    List<Group> findGroupByGroupName(String groupName);
    GroupMembership addGroupMembership(String username, GroupMembership groupMembership);
    Boolean removeGroupMembership(String username, int id);
    Boolean deleteGroupById(String username, int id);
    List<GroupRequests> findGroupRequestById(int id);
    List<GroupRequests> findAllGroupRequests();
    Boolean addRequest(GroupRequests groupRequests, String username);
    GroupMembership acceptRequest(int id, String username);
    void denyRequest(int id, String username);
}
