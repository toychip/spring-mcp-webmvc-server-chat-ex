package com.example.mcpservermvc.client;

import com.example.mcpservermvc.tool.dto.FlightOfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "amadeusFlights",
        url  = "https://test.api.amadeus.com",
        configuration = AmadeusAuthInterceptor.class)
public interface AmadeusFlightClient {

    @GetMapping("/v2/shopping/flight-offers")
    FlightOfferResponse offers(
            @RequestParam("originLocationCode")      String origin,
            @RequestParam("destinationLocationCode") String dest,
            @RequestParam("departureDate")           String depart,
            @RequestParam("returnDate")              String ret,
            @RequestParam("adults")                  int adults);
}
