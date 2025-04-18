package com.example.mcpservermvc.tool.dto;

public record FlightRangeRequest(
        String origin,          // ICN
        String destination,     // JFK
        String startDate,       // 2025‑12‑20
        String endDate,         // 2025‑12‑30
        int    adults,
        int    topN             // 날짜별 최저가 N개 (default 3)
) {}
