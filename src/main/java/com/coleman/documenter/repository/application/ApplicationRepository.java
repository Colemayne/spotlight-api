package com.coleman.documenter.repository.application;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.group.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
  List<Application> findApplicationById(int id);
  List<Application> findAll();
  List<Application> findApplicationByGroup(Group group);
}

