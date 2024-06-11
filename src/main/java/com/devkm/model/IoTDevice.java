package com.devkm.model;

import java.util.Date;

public class IoTDevice {
    private Date timestamp;
    private String vehicleId;
    private double latitude;
    private double longitude;
    private double speed;
    private String trafficLightStatus;
    private String environmentalConditions;
    private int trafficLightDuration;

    public IoTDevice() {
    }

    public IoTDevice(Date timestamp, String vehicleId, double latitude, double longitude, double speed, String trafficLightStatus, String environmentalConditions, int trafficLightDuration) {
        this.timestamp = timestamp;
        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.trafficLightStatus = trafficLightStatus;
        this.environmentalConditions = environmentalConditions;
        this.trafficLightDuration = trafficLightDuration;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTrafficLightStatus() {
        return trafficLightStatus;
    }

    public void setTrafficLightStatus(String trafficLightStatus) {
        this.trafficLightStatus = trafficLightStatus;
    }

    public String getEnvironmentalConditions() {
        return environmentalConditions;
    }

    public void setEnvironmentalConditions(String environmentalConditions) {
        this.environmentalConditions = environmentalConditions;
    }

    public int getTrafficLightDuration() {
        return trafficLightDuration;
    }

    public void setTrafficLightDuration(int trafficLightDuration) {
        this.trafficLightDuration = trafficLightDuration;
    }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "timestamp=" + timestamp +
                ", vehicleId='" + vehicleId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", trafficLightStatus='" + trafficLightStatus + '\'' +
                ", environmentalConditions='" + environmentalConditions + '\'' +
                ", trafficLightDuration=" + trafficLightDuration +
                '}';
    }
}

