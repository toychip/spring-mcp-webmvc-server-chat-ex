package com.example.mcpservermvc.tool;

import com.example.mcpservermvc.client.ClimateClient;
import com.example.mcpservermvc.tool.dto.ClimateResponse;
import com.example.mcpservermvc.tool.dto.DailyClimateData;
import com.example.mcpservermvc.tool.dto.FutureWeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    // … 기존 상수와 climateClient 그대로 …
    @Tool(              // 기존 getFutureWeather는 그대로 두셔도 됩니다
            name = "getFutureWeatherDaily",
            description = """
                    Return a list of daily mean temperature (°C) and precipitation (mm)
                    for each day between startDate and endDate (≤ 2050‑01‑01) at a given
                    latitude/longitude.
            """,
            returnDirect = true
    )
    public List<DailyClimateData> getFutureWeatherDaily(FutureWeatherRequest req) {

        ClimateResponse cr = climateClient.getClimate(
                req.latitude(),
                req.longitude(),
                req.startDate(),
                req.endDate(),
                MODELS,
                DAILY_VARS,
                TIMEZONE
        );

        // --- 배열 → DTO 리스트 매핑 ---
        List<String> times = cr.daily().time();
        List<Double> temps = cr.daily().temperature2mMean();
        List<Double> precs = cr.daily().precipitationSum();

        List<DailyClimateData> out = new ArrayList<>(times.size());
        for (int i = 0; i < times.size(); i++) {
            out.add(new DailyClimateData(times.get(i), temps.get(i), precs.get(i)));
        }
        return out;
    }
}
