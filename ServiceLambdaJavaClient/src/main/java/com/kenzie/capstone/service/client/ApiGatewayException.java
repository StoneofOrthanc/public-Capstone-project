package com.kenzie.capstone.service.client;

import com.amazonaws.AmazonServiceException;

/** This file created by Kenzie Academy **/

public class ApiGatewayException extends AmazonServiceException {
    public ApiGatewayException(String errorMessage) {
        super(errorMessage);
    }

    public ApiGatewayException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }
}
