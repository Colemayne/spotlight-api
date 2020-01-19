package com.coleman.documenter.service;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.group.Group;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApplicationService {

    Application addApplication(Application application);
    Application saveApplication(Application application);
    List<Application> findApplicationById(int id);
    List<Application> findApplicationByGroup(Group group);
    List<Application> findAllApplications();
    Endpoint addEndpoint(Endpoint endpoint);
    Endpoint findEndpointById(int id);
    List<Endpoint> findEndpointByApplication(Application application);
    Endpoint saveEndpoint(Endpoint endpoint);
    void deleteParameter(Integer id);
    void deleteHeader(Integer id);
    void deleteEnvironment(Integer id);
}
