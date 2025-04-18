package com.example.mcpservermvc.tool.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record FlightRangeRequest(
        @ToolParam(description = "출발 공항 IATA 코드 (예: ICN)", required = true)
        String origin,

        @ToolParam(description = "도착 공항 IATA 코드 (예: JFK)", required = true)
        String destination,

        @ToolParam(description = "조회 시작 날짜 (YYYY‑MM‑DD)", required = true)
        String startDate,

        @ToolParam(description = "조회 종료 날짜 (YYYY‑MM‑DD)", required = true)
        String endDate,

        @ToolParam(description = "성인 탑승객 수", required = true)
        int adults
) {}
