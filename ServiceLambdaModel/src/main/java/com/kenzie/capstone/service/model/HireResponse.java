package com.kenzie.capstone.service.model;

/** This file created by another team member **/

public class HireResponse {
    private String id;
    private String status;

    public HireResponse(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public HireResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HireResponse{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
