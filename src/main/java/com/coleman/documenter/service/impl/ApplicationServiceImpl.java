package com.coleman.documenter.service.impl;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.ApplicationEnvironment;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.application.endpoint.EndpointHeaders;
import com.coleman.documenter.domain.application.endpoint.EndpointParameters;
import com.coleman.documenter.domain.group.Group;
import com.coleman.documenter.repository.application.ApplicationEnvironmentRepository;
import com.coleman.documenter.repository.application.ApplicationRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointHeadersRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointParametersRepository;
import com.coleman.documenter.repository.application.endpoint.EndpointRepository;
import com.coleman.documenter.service.ApplicationService;
import com.coleman.documenter.service.GroupService;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Parameter;
import java.util.List;

@Component
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationEnvironmentRepository applicationEnvironmentRepository;
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
    public Application saveApplication(Application application) {
        int id = application.getId();
        Application application1 = applicationRepository.findById(id);
        application.setGroup(application1.getGroup());
        for(ApplicationEnvironment applicationEnvironment: application.getApplicationEnvironments()){
            if(applicationEnvironment.getEnvironmentName() != null && applicationEnvironment.getEnvironmentAddress() != null) {
                applicationEnvironment.setApplication(application);
                applicationEnvironmentRepository.save(applicationEnvironment);
            }
        }
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

    @Override
    public void getPDF() {

        String HTML = "<html><div style=\"position:relative;height:1000px;width:100%;background:rgba(0,0,0,0.3);\"><p>Hello</p></div>";

        StringBuilder sb = new StringBuilder(HTML);
        for(int i = 0; i < 5; i++) {
            sb.append("<div style=\"position:relative;height:700px;width:100%;background:rgba(0,0,0,0.3);\"><p>" + i + "</p></div>");
        }
        sb.append("</html>");
       // Document document = new Document();
        try {
            HtmlConverter.convertToPdf(sb.toString(), new FileOutputStream("/opt/testData/sampleHello.pdf"));
            System.out.println("did it!");
            /*PdfWriter.getInstance(document, new FileOutputStream("/opt/testData/sampleHello.pdf"));
            System.out.println("helllooo");
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("First PDF", font);
            document.add(chunk);
            document.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Transactional
    @Override
    public void deleteEnvironment(Integer id) {
        applicationEnvironmentRepository.deleteById(id);
    }


}
