package com.kenzie.capstone.service.model;

/** This file created by another team member **/

import java.util.Objects;

public class HireStatus {
    private String id;
    private String status;

    public HireStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public HireStatus() {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HireStatus that = (HireStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
