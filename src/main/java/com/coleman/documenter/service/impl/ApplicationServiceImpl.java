package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.application.endpoint.EndpointHeaders;
import com.coleman.documenter.domain.application.endpoint.EndpointParameters;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.repository.application.ApplicationRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointHeadersRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointParametersRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointRepository;
import com.coleman.documenter.service.ApplicationService;
import com.coleman.documenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Parameter;
import java.util.List;

@Component
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private EndpointRepository endpointRepository;
    @Autowired
    private EndpointParametersRepository endpointParametersRepository;
    @Autowired
    private EndpointHeadersRepository endpointHeadersRepository;
    @Autowired
    private GroupService groupService;

    @Override
    public Application addApplication(Application application) {
        Group group = groupService.findGroupById(application.getGroupId()).get(0);
        application.setGroup(group);
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> findApplicationById(int id) {
        return applicationRepository.findApplicationById(id);
    }

    @Override
    public List<Application> findApplicationByGroup(Group group){
        return applicationRepository.findApplicationByGroup(group);
    }

    @Override
    public List<Application> findAllApplications(){
        return applicationRepository.findAll();
    }

    @Override
    public Endpoint addEndpoint(Endpoint endpoint) {
        Application application = findApplicationById(endpoint.getApplicationId()).get(0);
        endpoint.setApplication(application);

        System.out.println(endpoint.getApplication().toString());
        System.out.println(endpoint.toString());
        return endpointRepository.save(endpoint);
    }

    @Override
    public Endpoint findEndpointById(int id) {
        return endpointRepository.findById(id);
    }

    @Override
    public List<Endpoint> findEndpointByApplication(Application application) {
        return endpointRepository.findByApplicationOrderByIdAsc(application);
    }

    @Override
    public Endpoint saveEndpoint(Endpoint endpoint){
        int id = endpoint.getId();
        Endpoint endpoint1 = endpointRepository.findById(id);
        endpoint.setApplication(endpoint1.getApplication());
        for(EndpointParameters parameter: endpoint.getEndpointParameters()){
            if(parameter.getParameterKey() != null && parameter.getParameterType() != null &&
               parameter.getParameterKey().length() != 0 && parameter.getParameterType().length() != 0) {
                parameter.setEndpoint(endpoint);
                endpointParametersRepository.save(parameter);
            }
        }
        for(EndpointHeaders header: endpoint.getEndpointHeaders()){
            if(header.getHeaderKey() != null && header.getHeaderType() != null &&
                    header.getHeaderKey().length() != 0 && header.getHeaderType() .length() != 0) {
                header.setEndpoint(endpoint);
                endpointHeadersRepository.save(header);
            }
        }
        return  endpointRepository.save(endpoint);
    }

    @Transactional
    @Override
    public void deleteParameter(Integer id) {
        endpointParametersRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteHeader(Integer id) {
        endpointHeadersRepository.deleteById(id);
    }


}
