package com.example.mcpservermvc.tool;

import com.example.mcpservermvc.client.ClimateClient;
import com.example.mcpservermvc.tool.dto.ClimateResponse;
import com.example.mcpservermvc.tool.dto.FutureWeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FutureWeatherTool {

    private static final String MODELS     = "EC_Earth3P_HR";
    private static final String DAILY_VARS = "temperature_2m_mean,precipitation_sum";
    private static final String TIMEZONE   = "Asia/Seoul";

    private final ClimateClient climateClient;

    /**
     * 과거 기후 모델 시뮬레이션을 바탕으로, 지정한 날짜의
     * 평균 기온(°C)과 일일 강수합(mm)을 예측하여 반환합니다.
     */
    @Tool(
            name = "getFutureWeather",
            description = """
            Predict future daily mean temperature (°C) and precipitation (mm)
            for any latitude/longitude and date (up to year 2050),
            based on 30-year climate model normals.
            """,
            returnDirect = true
    )
    public ClimateResponse getFutureWeather(FutureWeatherRequest req) {
        return climateClient.getClimate(
                req.latitude(),
                req.longitude(),
                req.date(), req.date(),
                MODELS,
                DAILY_VARS,
                TIMEZONE
        );
    }
}
