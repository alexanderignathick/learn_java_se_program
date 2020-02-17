package com.ignathick.hotel2.model.Room;

public enum Status {
    FREE("free"),
    BUSY("busy"),
    REPAIRED("repaired"),
    MAINTAINED("maintained");

    private final String stringValue;

    private String getStatusValue(){
        return this.stringValue;
    }

    Status(final String stringValue) {
        this.stringValue = stringValue;
    }

    public Status getStatusByStr(String statusStr){

        for (Status status:
                values()) {
            if (statusStr.equals(getStatusValue())) {
                return status;
            }
        }

        return null;

    }

}
