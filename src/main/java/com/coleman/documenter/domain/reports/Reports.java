package com.coleman.documenter.domain.reports;

import com.coleman.documenter.domain.application.ApplicationEnvironment;
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
import java.time.LocalDateTime;
import java.util.Set;

@Entity

@Table(name="reports")
public class Reports {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="report_name")
    private String reportName;
    @Column(name="report_description")
    private String reportDescription;
    @Column(name="report_date")
    private LocalDateTime reportDate;
    @Column(name="report_last_updated")
    private LocalDateTime reportLastUpdated;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="group_id")
    @JsonBackReference
    private Group group;
    @OneToMany(mappedBy = "report",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Set<ReportItem> reportItems;

    // Not part of the database.
    @Transient
    private Integer groupId;

    public Reports() {
    }

    public Reports(String reportName, String reportDescription, Integer groupId) {
        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public LocalDateTime getReportLastUpdated() {
        return reportLastUpdated;
    }

    public void setReportLastUpdated(LocalDateTime reportLastUpdated) {
        this.reportLastUpdated = reportLastUpdated;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<ReportItem> getReportItems() {
        return reportItems;
    }

    public void setReportItems(Set<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", reportName='" + reportName + '\'' +
                ", reportDescription='" + reportDescription + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", reportLastUpdated='" + reportLastUpdated + '\'' +
                ", group=" + group +
                ", reportItems=" + reportItems +
                ", groupId=" + groupId +
                '}';
    }
}
