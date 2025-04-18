package com.example.mcpservermvc.tool;

import com.example.mcpservermvc.client.AmadeusFlightClient;
import com.example.mcpservermvc.tool.dto.FlightOfferResponse;
import org.springframework.stereotype.Component;

@Component
public class AmadeusSearchAdapter {

    private final AmadeusFlightClient client;

    public AmadeusSearchAdapter(AmadeusFlightClient client) { this.client = client; }

    /** 실제 HTTP 1회 호출에 RateLimiter·Retry 적용 */
    @io.github.resilience4j.ratelimiter.annotation.RateLimiter(name = "amadeus")
    @io.github.resilience4j.retry.annotation.Retry(name = "amadeus")
    public FlightOfferResponse search(String o, String d, String dep, String ret, int adults){
        return client.offers(o,d,dep,ret,adults);
    }
}

