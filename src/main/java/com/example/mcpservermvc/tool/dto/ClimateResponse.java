package com.example.mcpservermvc.tool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Open‑Meteo Climate API 응답을 매핑하는 record
 */
public record ClimateResponse(
        double latitude,
        double longitude,
        @JsonProperty("generationtime_ms")
        double generationTimeMs,
        @JsonProperty("utc_offset_seconds")
        int utcOffsetSeconds,
        String timezone,
        @JsonProperty("timezone_abbreviation")
        String timezoneAbbreviation,
        double elevation,
        @JsonProperty("daily_units")
        DailyUnits dailyUnits,
        DailyData daily
) {
    public record DailyUnits(
            String time,
            @JsonProperty("temperature_2m_mean") String temperature2mMean,
            @JsonProperty("precipitation_sum")   String precipitationSum
    ) {}

    public record DailyData(
            List<String> time,
            @JsonProperty("temperature_2m_mean")
            List<Double> temperature2mMean,
            @JsonProperty("precipitation_sum")
            List<Double> precipitationSum
    ) {}
}
