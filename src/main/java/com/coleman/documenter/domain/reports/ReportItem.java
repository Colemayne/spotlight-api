package com.coleman.documenter.domain.reports;

import com.coleman.documenter.domain.application.Application;
import com.coleman.documenter.domain.application.ApplicationEnvironment;
import com.coleman.documenter.domain.application.endpoint.Endpoint;
import com.coleman.documenter.domain.group.Group;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="report_items")
public class ReportItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="application_id")
    @JsonBackReference(value="application")
    private Application application;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="endpoint_id")
    @JsonBackReference(value="endpoint")
    private Endpoint endpoint;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="report_id")
    @JsonBackReference(value="report")
    private Reports report;

    // Not part of the database.
    @Transient
    private Integer reportId;
    @Transient
    private Integer applicationId;
    @Transient
    private Integer endpointId;

    public ReportItem() {
    }

    public ReportItem(Reports report) {
        this.report = report;
    }

    public ReportItem(Integer reportId, Integer applicationId, Integer endpointId) {
        this.reportId = reportId;
        this.applicationId = applicationId;
        this.endpointId = endpointId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Reports getReport() {
        return report;
    }

    public void setReport(Reports report) {
        this.report = report;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Integer endpointId) {
        this.endpointId = endpointId;
    }
}
