package com.coleman.documenter.repository.group;

import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.domain.group.GroupMembership;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMembershipRepository extends CrudRepository<GroupMembership, Integer> {
    List<GroupMembership> findAll();
    boolean existsByUsernameAndGroup(String username, Group group);
}

