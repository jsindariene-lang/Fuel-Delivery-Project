package org.example;

import java.math.BigDecimal;

public class Shipment {

    private String id;
    private String type;
    private String client;
    private String date;
    private int volume;
    private double distance;
    private BigDecimal basePrice = BigDecimal.ZERO;

    // Reikalingas Jackson JSON nuskaitymui
    public Shipment() {
    }

    public Shipment(String type, String client, String date) {
        this.type = type;
        this.client = client;
        this.date = date;
    }

    // Getters

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getClient() {
        return client;
    }

    public String getDate() {
        return date;
    }

    public int getVolume() {
        return volume;
    }

    public double getDistance() {
        return distance;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}