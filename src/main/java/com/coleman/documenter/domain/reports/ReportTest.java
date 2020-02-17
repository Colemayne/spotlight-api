package com.coleman.documenter.domain.reports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="report_test")
public class ReportTest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="rid")
    private Integer rid;
    @Column(name="aid")
    private Integer aid;
    @Column(name="eid")
    private Integer eid;

    public ReportTest() {
    }

    public ReportTest(Integer rid, Integer aid, Integer eid) {
        this.rid = rid;
        this.aid = aid;
        this.eid = eid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
