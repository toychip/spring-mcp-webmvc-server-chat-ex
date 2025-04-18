package com.example.mcpservermvc.tool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FlightOfferResponse(
        List<Offer> data
) {
    public record Offer(
            String id,
            Price price,
            List<Itinerary> itineraries
    ) {}
    public record Price(
            @JsonProperty("grandTotal") String grandTotal,
            @JsonProperty("currency")   String currency,
            @JsonProperty("total")      String total,
            @JsonProperty("base")       String base
    ) {}

    public record Itinerary(
            List<Segment> segments
    ) {}
    public record Segment(
            @JsonProperty("departure") FlightPoint departure,
            @JsonProperty("arrival")   FlightPoint arrival,
            @JsonProperty("carrierCode") String carrierCode,
            @JsonProperty("duration")    String duration
    ) {}
    public record FlightPoint(
            @JsonProperty("iataCode") String iataCode,
            @JsonProperty("at")       String time
    ) {}

    public record DailyOffers(
            String date,                       // YYYY‑MM‑DD
            List<FlightOfferResponse.Offer> offers
    ) {}

}

