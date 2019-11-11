package com.visualmeta.feeder.model;

public class Message {
    public enum StatusCode {
        OK("OK"),
        FAILED("FAILED");

        private String code;

        StatusCode(String code){
            this.code = code;
        }
    }

    private StatusCode status;
    private String message;

    public Message(StatusCode status) {
        this.status = status;
        this.message = status.code;
    }

    public Message(StatusCode status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusCode getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Message)) {
            return false;
        }

        Message message = (Message) object;

        return this.getMessage().equals(message.getMessage()) &&
                this.getStatus().equals(message.getStatus());
    }
}
