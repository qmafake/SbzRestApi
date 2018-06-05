/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isorest.domain;

/**
 *
 * @author Artwell Mamvura
 */
public class StewardBankApiResponse<T> {

    private static final long serialVersionUID = -0x32EA84E37FFB508EL;

    private String statusCode;
    private String message;
    private T responseBody;
    

    public StewardBankApiResponse() {
        statusCode = StewardBankConstants.StatusCodes.SUCCESS;
        message = StewardBankConstants.Messages.OK;
    }

    public StewardBankApiResponse(final String statusCode, final String message, final T responseBody) {
        this.statusCode = statusCode;
        this.message = message;
        this.responseBody = responseBody;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "StewardBankApiResponse [statusCode=" + statusCode
                + ", message=" + message + ", responseBody=" + responseBody
                + "]";
    }

}
