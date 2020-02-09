package com.coleman.documenter.repository.group;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.group.GroupRequests;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRequestsRepository extends CrudRepository<GroupRequests, Integer> {
    List<GroupRequests> findAll();
    List<GroupRequests> findGroupRequestByGroupId(int id);
    boolean existsByUsernameAndGroupId(String username, int groupId);
    GroupRequests findGroupRequestById(int id);
}

