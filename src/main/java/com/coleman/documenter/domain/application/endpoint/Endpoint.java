package com.coleman.documenter.domain.application.endpoint;

import com.coleman.documenter.domain.application.Application;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Table(name="endpoints")
public class Endpoint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="endpoint_version")
    private String endpointVersion;
    @Column(name="endpoint_location")
    private String endpointLocation;
    @Column(name="endpoint_name")
    private String endpointName;
    @Column(name="endpoint_description")
    private String endpointDescription;
    @Column(name="endpoint_method")
    private String endpointMethod;
    @Column(name="endpoint_port")
    private String endpointPort;
    @Column(name="endpoint_expectation")
    private String endpointExpectation;
    @Column(name="endpoint_notes")
    private String endpointNotes;
    @Column(name="endpoint_auth_required")
    private Integer endpointAuthRequired;
    @Column(name="endpoint_example_body")
    private String endpointExampleBody;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="application_id")
    @JsonBackReference
    private Application application;
    @OneToMany(mappedBy = "endpoint",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<EndpointParameters> endpointParameters;
    @OneToMany(mappedBy = "endpoint",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<EndpointHeaders> endpointHeaders;

    @Transient
    private Integer applicationId;

    public Endpoint() {
    }

    public Endpoint(String endpointVersion, String endpointName, String endpointLocation,
                    String endpointDescription, String endpointMethod, String endpointPort,String endpointExpectation,
                    String endpointNotes, Integer endpointAuthRequired, String endpointExampleBody, Application application) {
        this.endpointVersion = endpointVersion;
        this.endpointName = endpointName;
        this.endpointLocation = endpointLocation;
        this.endpointDescription = endpointDescription;
        this.endpointMethod = endpointMethod;
        this.endpointPort = endpointPort;
        this.endpointExpectation = endpointExpectation;
        this.endpointNotes = endpointNotes;
        this.endpointAuthRequired = endpointAuthRequired;
        this.endpointExampleBody = endpointExampleBody;
        this.application = application;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndpointVersion() {
        return endpointVersion;
    }

    public void setEndpointVersion(String endpointVersion) {
        this.endpointVersion = endpointVersion;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getEndpointLocation() {
        return endpointLocation;
    }

    public void setEndpointLocation(String endpointLocation) {
        this.endpointLocation = endpointLocation;
    }

    public String getEndpointDescription() {
        return endpointDescription;
    }

    public void setEndpointDescription(String endpointDescription) {
        this.endpointDescription = endpointDescription;
    }

    public String getEndpointMethod() {
        return endpointMethod;
    }

    public void setEndpointMethod(String endpointMethod) {
        this.endpointMethod = endpointMethod;
    }

    public String getEndpointPort() {
        return endpointPort;
    }

    public void setEndpointPort(String endpointPort) {
        this.endpointPort = endpointPort;
    }

    public String getEndpointExpectation() {
        return endpointExpectation;
    }

    public void setEndpointExpectation(String endpointExpectation) {
        this.endpointExpectation = endpointExpectation;
    }

    public String getEndpointNotes() {
        return endpointNotes;
    }

    public void setEndpointNotes(String endpointNotes) {
        this.endpointNotes = endpointNotes;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Set<EndpointParameters> getEndpointParameters() {
        return endpointParameters;
    }

    public void setEndpointParameters(Set<EndpointParameters> endpointParameters) {
        this.endpointParameters = endpointParameters;
    }

    public Integer getEndpointAuthRequired() {
        return endpointAuthRequired;
    }

    public void setEndpointAuthRequired(Integer endpointAuthRequired) {
        this.endpointAuthRequired = endpointAuthRequired;
    }

    public String getEndpointExampleBody() {
        return endpointExampleBody;
    }

    public void setEndpointExampleBody(String endpointExampleBody) {
        this.endpointExampleBody = endpointExampleBody;
    }

    public Set<EndpointHeaders> getEndpointHeaders() {
        return endpointHeaders;
    }

    public void setEndpointHeaders(Set<EndpointHeaders> endpointHeaders) {
        this.endpointHeaders = endpointHeaders;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "id=" + id +
                ", endpointVersion='" + endpointVersion + '\'' +
                ", endpointLocation='" + endpointLocation + '\'' +
                ", endpointName='" + endpointName + '\'' +
                ", endpointDescription='" + endpointDescription + '\'' +
                ", endpointMethod='" + endpointMethod + '\'' +
                ", endpointPort='" + endpointPort + '\'' +
                ", endpointExpectation='" + endpointExpectation + '\'' +
                ", endpointNotes='" + endpointNotes + '\'' +
                ", endpointParameters=" + endpointParameters +
                ", endpointHeaders=" + endpointHeaders +
                ", applicationId=" + applicationId +
                '}';
    }
}
