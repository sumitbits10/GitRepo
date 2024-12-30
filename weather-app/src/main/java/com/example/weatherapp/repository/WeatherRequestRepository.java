package com.example.weatherapp.repository;

import com.example.weatherapp.model.WeatherRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRequestRepository extends JpaRepository<WeatherRequest, Long> {
    List<WeatherRequest> findByPostalCode(String postalCode);
    List<WeatherRequest> findByUserName(String userName);
}