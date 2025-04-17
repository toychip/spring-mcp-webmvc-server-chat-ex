package com.example.mcpservermvc.client;

import com.example.mcpservermvc.tool.dto.ClimateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "climateClient", url = "https://climate-api.open-meteo.com")
public interface ClimateClient {

    @GetMapping("/v1/climate")
    ClimateResponse getClimate(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date")   String endDate,
            @RequestParam("models")     String models,
            @RequestParam("daily")      String dailyVars,
            @RequestParam("timezone")   String timezone
    );
}
