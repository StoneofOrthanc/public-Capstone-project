package com.kenzie.appserver.service.model;

/** This file was created by another team member **/

public class Freelancer {
    private final String id;
    private final String name;
    private final String expertise;
    private final String rate;
    private final String location;
    private final String contact;

    public Freelancer(String id, String name, String expertise, String rate, String location, String contact){
        this.id = id;
        this.name = name;
        this.expertise = expertise;
        this.rate = rate;
        this.location = location;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getRate() {
        return rate;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }
}
