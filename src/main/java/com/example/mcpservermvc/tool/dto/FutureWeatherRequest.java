package com.example.mcpservermvc.tool.dto;

import org.springframework.ai.tool.annotation.ToolParam;

/**
 * LLM 도구용 입력 파라미터
 */
public record FutureWeatherRequest(
        @ToolParam(description = "위도 (예: 37.57)", required = true)
        double latitude,

        @ToolParam(description = "경도 (예: 126.98)", required = true)
        double longitude,

        @ToolParam(description = "조회할 날짜 (YYYY-MM-DD)", required = true)
        String date
) {}
