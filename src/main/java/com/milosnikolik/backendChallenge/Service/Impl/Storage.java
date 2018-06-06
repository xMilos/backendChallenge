package com.milosnikolik.backendChallenge.Service.Impl;

import com.milosnikolik.backendChallenge.Model.Timing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by User on 07.6.2018.
 */
@Component
public class Storage {


    private final Timing[] store;

    Storage(@Value("${interval.milliseconds}") int interval) {
        this.store = new Timing[interval];
    }

    synchronized void persist(long currentInstant, Timing toPersist) {
        int index = getIndex(currentInstant - toPersist.getTimestamp());
        Timing previous = ofNullable(store[index]).orElse(new Timing());
        if (previous.getTimestamp() == toPersist.getTimestamp()) {
            DoubleSummaryStatistics combined = previous.getStatistics();
            combined.combine(toPersist.getStatistics());
            store[index] = new Timing(previous.getTimestamp(), combined);
        } else {
            store[index] = toPersist;
        }
    }

    synchronized List<DoubleSummaryStatistics> findStatisticsUpTo(long instant) {
        return IntStream.range(0, store.length)
                .filter(i -> ofNullable(store[i]).isPresent())
                .filter(i -> (instant - store[i].getTimestamp()) < store.length)
                .mapToObj(i -> store[i].getStatistics())
                .collect(toList());
    }



    private int getIndex(long time) {
        return (int) (time) % store.length;
    }
}
