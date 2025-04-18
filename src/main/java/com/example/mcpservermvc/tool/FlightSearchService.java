package com.example.mcpservermvc.tool;

import com.example.mcpservermvc.tool.dto.FlightOfferResponse.DailyOffers;
import com.example.mcpservermvc.tool.dto.FlightRangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchService {

    private final AmadeusSearchAdapter search;

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
                .limit(r.topN())
                .toList();
        return new DailyOffers(d.toString(), sorted);
    }
}
