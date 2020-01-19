package com.coleman.documenter.domain.application;

import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.group.Group;
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

@Table(name="applications")
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="application_name")
    private String applicationName;
    @Column(name="application_description")
    private String applicationDescription;
    @Column(name="application_port")
    private String applicationPort;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="group_id")
    @JsonBackReference
    private Group group;
    @OneToMany(mappedBy = "application",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<Endpoint> endpoints;
    @OneToMany(mappedBy = "application",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<ApplicationEnvironment> applicationEnvironments;

    // Not part of the database.
    @Transient
    private Integer groupId;

    public Application() {
    }

    public Application(String applicationName, String applicationDescription, String applicationPort, Integer groupId) {
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
        this.applicationPort = applicationPort;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(Set<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public Set<ApplicationEnvironment> getApplicationEnvironments() {
        return applicationEnvironments;
    }

    public void setApplicationEnvironments(Set<ApplicationEnvironment> applicationEnvironments) {
        this.applicationEnvironments = applicationEnvironments;
    }

    public String getApplicationPort() {
        return applicationPort;
    }

    public void setApplicationPort(String applicationPort) {
        this.applicationPort = applicationPort;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicationName='" + applicationName + '\'' +
                ", applicationDescription='" + applicationDescription + '\'' +
                ", applicationPort='" + applicationPort + '\'' +
                ", group=" + group +
                ", endpoints=" + endpoints +
                ", groupId=" + groupId +
                '}';
    }
}
