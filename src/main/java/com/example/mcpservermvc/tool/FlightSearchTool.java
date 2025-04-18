package com.example.mcpservermvc.tool;

import com.example.mcpservermvc.tool.dto.FlightOfferResponse.DailyOffers;
import com.example.mcpservermvc.tool.dto.FlightRangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchTool {

    private final AmadeusSearchAdapter search;

    @Tool(                                         // LLM‑노출 메서드
            name = "getCheapestFlightsByDate",
            description = """
            Return, for each day between startDate and endDate,
            the top‑3 cheapest flight offers (adult passenger count specified)
            including total price, currency, and basic itinerary info.
            """,
            returnDirect = true                       // 결과를 LLM 응답에 바로 삽입
    )
    public List<DailyOffers> searchRange(FlightRangeRequest r) {
        LocalDate s = LocalDate.parse(r.startDate());
        LocalDate e = LocalDate.parse(r.endDate());

        return s.datesUntil(e.plusDays(1))
                .map(d -> queryOneDay(r, d))
                .filter(o -> !o.offers().isEmpty())
                .sorted(Comparator.comparing(DailyOffers::date))
                .toList();
    }

    private DailyOffers queryOneDay(FlightRangeRequest r, LocalDate d) {
        var resp = search.search(r.origin(), r.destination(), d.toString(),
                r.endDate(), r.adults());
        var sorted = resp.data().stream()
                .sorted(Comparator.comparing(o -> new BigDecimal(o.price().grandTotal())))
                .limit(3)
                .toList();
        return new DailyOffers(d.toString(), sorted);
    }
}
