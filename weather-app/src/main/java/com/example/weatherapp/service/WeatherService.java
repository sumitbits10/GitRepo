package com.example.weatherapp.service;

import com.example.weatherapp.model.User;
import com.example.weatherapp.model.WeatherRequest;
import com.example.weatherapp.repository.UserRepository;
import com.example.weatherapp.repository.WeatherRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final UserRepository userRepository;
    private final WeatherRequestRepository weatherRequestRepository;

    public String getWeather(String postalCode, String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.isActive()) {
            throw new IllegalStateException("User is not active");
        }

        String url = String.format("https://api.openweathermap.org/data/2.5/weather?zip=%s&appid=%s", postalCode, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setUser(user);
        weatherRequest.setPostalCode(postalCode);
        weatherRequest.setWeatherConditions(response);
        weatherRequestRepository.save(weatherRequest);

        return response;
    }

    public List<WeatherRequest> getHistory(String postalCode) {
        return weatherRequestRepository.findByPostalCode(postalCode);
    }
}