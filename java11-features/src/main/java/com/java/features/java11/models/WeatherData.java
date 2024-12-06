package com.java.features.java11.models;

public class WeatherData {
    private final String city;
    private final String condition;
    private final int temperature;

    public WeatherData(String city, String condition, int temperature) {
        this.city = city;
        this.condition = condition;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public String getCondition() {
        return condition;
    }

    public int getTemperature() {
        return temperature;
    }
}
