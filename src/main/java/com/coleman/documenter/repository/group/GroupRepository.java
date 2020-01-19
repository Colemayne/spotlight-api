package com.coleman.documenter.repository.group;

import com.coleman.documenter.domain.group.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Integer> {
  List<Group> findGroupById(int id);
  List<Group> findAll();
  List<Group> findGroupByGroupName(String groupName);
}

