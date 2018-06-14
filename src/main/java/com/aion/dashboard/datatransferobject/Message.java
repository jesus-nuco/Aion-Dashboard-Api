package com.aion.dashboard.datatransferobject;

public class Message {

    private String errorCode;
    private String errorMessage;


    public Message(){}

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

    @Override
    public boolean equals(Object that){
        if (that == null) return false;
        else if (! ((Message) that).errorMessage.equals(this.errorMessage)) return false;
        else if (! ((Message) that).errorCode.equals(this.errorCode)) return false;
        else return true;
    }

    @Override
    public String toString(){
        return "{'errorCode':" + errorCode +", 'errorMessage':" + errorMessage + "}";
    }
}
