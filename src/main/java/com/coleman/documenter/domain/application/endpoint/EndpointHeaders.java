package com.coleman.documenter.domain.application.endpoint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="endpoint_headers")
public class EndpointHeaders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="header_key")
    private String headerKey;
    @Column(name="header_type")
    private String headerType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="endpoint_id")
    @JsonBackReference
    private Endpoint endpoint;

    public EndpointHeaders() {
    }

    public EndpointHeaders(String headerKey, String headerType, Endpoint endpoint) {
        this.headerKey = headerKey;
        this.headerType = headerType;
        this.endpoint = endpoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getHeaderType() {
        return headerType;
    }

    public void setHeaderType(String headerType) {
        this.headerType = headerType;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }
}
