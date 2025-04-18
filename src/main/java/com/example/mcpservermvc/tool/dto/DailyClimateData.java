package com.example.mcpservermvc.tool.dto;

public record DailyClimateData(
        String date,          // ISO‑8601 (YYYY‑MM‑DD)
        Double meanTemp,      // °C
        Double precipitation  // mm
) {}
