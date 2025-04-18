package com.example.mcpservermvc.controller;

import com.example.mcpservermvc.tool.FlightSearchService;
import com.example.mcpservermvc.tool.dto.FlightOfferResponse.DailyOffers;
import com.example.mcpservermvc.tool.dto.FlightRangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Deprecated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @GetMapping("/range")
    public List<DailyOffers> searchRange(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "1") int adults,
            @RequestParam(defaultValue = "3") int topN) {

        return flightSearchService.searchRange(new FlightRangeRequest(
                origin, destination, startDate, endDate, adults, topN));
    }

}
