package com.coleman.documenter.service;

import com.coleman.documenter.domain.group.Group;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupService {
    Group createGroup(String groupName);
    List<Group> findGroupById(int id);
    List<Group> findAllGroups();
    List<Group> findGroupByGroupName(String groupName);
}
