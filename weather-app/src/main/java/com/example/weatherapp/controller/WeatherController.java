package com.example.weatherapp.controller;

import com.example.weatherapp.model.WeatherRequest;
import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam String postalCode, @RequestParam String user) {
        return weatherService.getWeather(postalCode, user);
    }

    @GetMapping("/history")
    public List<WeatherRequest> getHistory(@RequestParam String postalCode) {
        return weatherService.getHistory(postalCode);
    }
}