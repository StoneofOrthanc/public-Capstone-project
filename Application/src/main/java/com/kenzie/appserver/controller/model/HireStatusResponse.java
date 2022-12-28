package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** This file was created by another team member **/

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HireStatusResponse {

    @JsonProperty("hirestatus")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
