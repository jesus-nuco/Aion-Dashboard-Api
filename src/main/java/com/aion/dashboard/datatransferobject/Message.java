package com.aion.dashboard.datatransferobject;

public class Message {

    private String errorCode;
    private String errorMessage;


    /**
     * Object used to define errors that have occured within the API
     * @param errorCode to be returned to the client
     * @param errorMessage to be returned to the client
     */
    public Message(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public Message setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Message setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
