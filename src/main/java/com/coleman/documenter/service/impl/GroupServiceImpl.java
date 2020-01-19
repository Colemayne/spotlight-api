package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.repository.group.GroupMembershipRepository;
import com.coleman.documenter.repository.group.GroupRepository;
import com.coleman.documenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMembershipRepository groupMembershipRepository;

    // Groups

    @Override
    public Group createGroup(String groupName) {
        return groupRepository.save(new Group(groupName));
    }

    @Override
    public List<Group> findGroupById(int id) {
        return groupRepository.findGroupById(id);
    }

    @Override
    public List<Group> findGroupByGroupName(String groupName) {
        return groupRepository.findGroupByGroupName(groupName);
    }

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }


    // Group Membership



}
