package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.businessResponses.TailoredGroups;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.group.GroupMembership;
import com.coleman.documenter.domain.group.GroupRequests;
import com.coleman.documenter.repository.group.GroupMembershipRepository;
import com.coleman.documenter.repository.group.GroupRepository;
import com.coleman.documenter.repository.group.GroupRequestsRepository;
import com.coleman.documenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMembershipRepository groupMembershipRepository;
    @Autowired
    private GroupRequestsRepository groupRequestsRepository;

    // Groups

    @Override
    public Group createGroup(String groupName, String username) {
        Group group = groupRepository.save(new Group(groupName));
        groupMembershipRepository.save(new GroupMembership(username, "OWNER", group));
        return group;
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
    @Override
    public TailoredGroups findTailoredGroups(String username) {
        List<Group> groupsList = groupRepository.findAll();
        List<Group> myGroups = new ArrayList<>();
        List<Group> otherGroups = new ArrayList<>();

        for(Group group: groupsList) {
            if(groupMembershipRepository.existsByUsernameAndGroup(username, group)) {
                myGroups.add(group);
            } else {
                otherGroups.add(group);
            }
        }
        return new TailoredGroups(myGroups,otherGroups);
    }

    @Override
    @Transactional
    public Boolean deleteGroupById(String username, int id) {
        Group group = findGroupById(id).get(0);
        if(groupMembershipRepository.existsByUsernameAndGroup(username, group)) {
            groupRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GroupMembership addGroupMembership(String username, GroupMembership groupMembership){
        Group group = groupRepository.findGroupById(Integer.valueOf(groupMembership.getGroup().getGroupName())).get(0);
        groupMembership.setGroup(group);
        if(groupMembershipRepository.existsByUsernameAndGroup(username, groupMembership.getGroup())
           && !groupMembershipRepository.existsByUsernameAndGroup(groupMembership.getUsername(), groupMembership.getGroup())) {
            return groupMembershipRepository.save(groupMembership);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean removeGroupMembership(String username, int id) {
        try {
            Optional<GroupMembership> groupMembership = groupMembershipRepository.findById(id);
            if(groupMembership.isPresent()) {
                Group group = groupRepository.findGroupById(groupMembership.get().getGroup().getId()).get(0);
                if (groupMembershipRepository.existsByUsernameAndGroup(username, group)) {
                    groupMembership.ifPresent(membership -> groupMembershipRepository.delete(membership));
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Not Found: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<GroupRequests> findAllGroupRequests() {
        return groupRequestsRepository.findAll();
    }

    @Override
    public List<GroupRequests> findGroupRequestById(int id) {
        return groupRequestsRepository.findGroupRequestByGroupId(id);
    }

    @Override
    public Boolean addRequest(GroupRequests groupRequests, String username) {
        Group group = groupRepository.findGroupById(groupRequests.getGroupId()).get(0);
        try {
            if(groupRequestsRepository.existsByUsernameAndGroupId(username, groupRequests.getGroupId())) {
                System.out.println("User: "+username+" has already requested this group.");
                return false;
            }
            groupRequestsRepository.save(groupRequests);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("User: "+username+" did not have permission to add "+groupRequests.getUsername()+" to group with groupId: "+groupRequests.getGroupId());
            return false;
        }
    }

    @Override
    public GroupMembership acceptRequest(int id, String username) {
        GroupRequests groupRequest = groupRequestsRepository.findGroupRequestById(id);
        Group group = groupRepository.findGroupById(groupRequest.getGroupId()).get(0);
        String requester = groupRequest.getUsername();
        GroupMembership groupMembership = new GroupMembership(requester, "ADMIN", group);
        groupRequestsRepository.delete(groupRequest);
        return groupMembershipRepository.save(groupMembership);
    }

    @Override
    @Transactional
    public void denyRequest(int id, String username) {
        GroupRequests groupRequest = groupRequestsRepository.findGroupRequestById(id);
        Group group = groupRepository.findGroupById(groupRequest.getGroupId()).get(0);
        if(groupMembershipRepository.existsByUsernameAndGroup(username, group)){
            groupRequestsRepository.delete(groupRequest);
        }
    }

}
