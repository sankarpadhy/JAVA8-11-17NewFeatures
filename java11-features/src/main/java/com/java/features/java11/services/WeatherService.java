package com.java.features.java11.services;

import com.java.features.java11.models.WeatherData;
import java.util.concurrent.CompletableFuture;

public class WeatherService {
    public CompletableFuture<WeatherData> getWeatherAsync(String city) {
        // Simulate an asynchronous API call
        return CompletableFuture.supplyAsync(() -> new WeatherData(city, "Sunny", 25));
    }
}
