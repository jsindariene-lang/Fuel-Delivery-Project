package org.example;

public class ErrorRecord {

    private String shipmentId;
    private String errorCode;

    public ErrorRecord(String shipmentId, String errorCode) {
        this.shipmentId = shipmentId;
        this.errorCode = errorCode;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public String getErrorCode() {
        return errorCode;
    }
}