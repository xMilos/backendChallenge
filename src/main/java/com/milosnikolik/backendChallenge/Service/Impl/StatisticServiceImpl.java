package com.milosnikolik.backendChallenge.Service.Impl;

import com.milosnikolik.backendChallenge.Model.Statistics;
import com.milosnikolik.backendChallenge.Model.Timing;
import com.milosnikolik.backendChallenge.Model.Transaction;
import com.milosnikolik.backendChallenge.Service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import static java.time.Instant.now;
/**
 * Created by User on 06.6.2018.
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    
    private final Storage storage;

    @Autowired
    public StatisticServiceImpl(Storage storage) {
        this.storage = storage;
    }

    public Statistics getStatistics() {
        List<DoubleSummaryStatistics> statsSummary = storage.findStatisticsUpTo(now().toEpochMilli());
        return Statistics.of(statsSummary
                .stream().reduce(this::combine).orElse(new DoubleSummaryStatistics()));
    }

    private DoubleSummaryStatistics combine(DoubleSummaryStatistics first, DoubleSummaryStatistics second) {
        first.combine(second);
        return first;
    }
}
