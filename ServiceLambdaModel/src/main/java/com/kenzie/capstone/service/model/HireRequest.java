package com.kenzie.capstone.service.model;

/** This file created by another team member **/

public class HireRequest {
    private String id;
    private String status;

    public HireRequest(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public HireRequest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String freelancerId) {
        this.id = freelancerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HireRequest{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
