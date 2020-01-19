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
@Table(name="endpoint_parameters")
public class EndpointParameters {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="parameter_key")
    private String parameterKey;
    @Column(name="parameter_type")
    private String parameterType;
    @Column(name="parameter_description")
    private String parameterDescription;
    @Column(name="parameter_required")
    private Integer parameterRequired;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="endpoint_id")
    @JsonBackReference
    private Endpoint endpoint;

    public EndpointParameters() {
    }

    public EndpointParameters(String parameterKey, String parameterType, String parameterDescription, Integer parameterRequired, Endpoint endpoint) {
        this.parameterKey = parameterKey;
        this.parameterType = parameterType;
        this.parameterDescription = parameterDescription;
        this.parameterRequired = parameterRequired;
        this.endpoint = endpoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public Integer getParameterRequired() {
        return parameterRequired;
    }

    public void setParameterRequired(Integer parameterRequired) {
        this.parameterRequired = parameterRequired;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint= endpoint;
    }


}
