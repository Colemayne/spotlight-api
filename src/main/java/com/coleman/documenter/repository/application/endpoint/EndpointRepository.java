package com.coleman.documenter.repository.application.endpoint;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EndpointRepository extends CrudRepository<Endpoint, Integer> {
  List<Endpoint> findByApplicationOrderByIdAsc(Application application);
  Endpoint findById(int id);
}

