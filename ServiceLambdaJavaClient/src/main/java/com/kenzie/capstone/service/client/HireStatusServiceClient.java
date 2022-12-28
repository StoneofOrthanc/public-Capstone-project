package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.HireRequest;
import com.kenzie.capstone.service.model.HireResponse;
import com.kenzie.capstone.service.model.HireStatus;

/** This file created by another team member **/

public class HireStatusServiceClient {
    private static final String GET_HIRESTATUS_ENDPOINT = "hirestatus/{id}";
    private static final String SET_HIRESTATUS_ENDPOINT = "hirestatus/set";

    private ObjectMapper mapper;

    public HireStatusServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public HireStatus getHireStatus(String freelancerId) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_HIRESTATUS_ENDPOINT.replace("{id}", freelancerId));
        HireStatus hireStatus;
        try {
            hireStatus = mapper.readValue(response, HireStatus.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return hireStatus;
    }

    public HireResponse setHireStatus(HireRequest hireRequest) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String request;
        try {
            request = mapper.writeValueAsString(hireRequest);
        } catch(JsonProcessingException e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        String response = endpointUtility.postEndpoint(SET_HIRESTATUS_ENDPOINT, request);
        HireResponse hireResponse;
        try {
            hireResponse = mapper.readValue(response, HireResponse.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return hireResponse;
    }
}
