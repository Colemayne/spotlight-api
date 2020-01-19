package com.coleman.documenter.domain.application;

import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.group.Group;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Table(name="application_environments")
public class ApplicationEnvironment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="environment_name")
    private String environmentName;
    @Column(name="environment_proxy")
    private Integer environmentProxy;
    @Column(name="environment_description")
    private String environmentDescription;
    @Column(name="environment_address")
    private String environmentAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="application_id")
    @JsonBackReference
    private Application application;

    // Not part of the database.
    @Transient
    private Integer applicationId;

    public ApplicationEnvironment() {
    }

    public ApplicationEnvironment(String environmentName, String environmentAddress,Integer environmentProxy,
                                  String environmentDescription, Integer applicationId) {
        this.environmentName = environmentName;
        this.environmentAddress = environmentAddress;
        this.environmentProxy = environmentProxy;
        this.environmentDescription = environmentDescription;
        this.applicationId = applicationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentAddress() {
        return environmentAddress;
    }

    public void setEnvironmentAddress(String environmentAddress) {
        this.environmentAddress = environmentAddress;
    }

    public Integer getEnvironmentProxy() {
        return environmentProxy;
    }

    public void setEnvironmentProxy(Integer environmentProxy) {
        this.environmentProxy = environmentProxy;
    }

    public String getEnvironmentDescription() {
        return environmentDescription;
    }

    public void setEnvironmentDescription(String environmentDescription) {
        this.environmentDescription = environmentDescription;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
}
