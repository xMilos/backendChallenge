package com.milosnikolik.backendChallenge.Service.Impl;

import com.milosnikolik.backendChallenge.Model.Timing;
import com.milosnikolik.backendChallenge.Model.Transaction;
import com.milosnikolik.backendChallenge.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

import static java.time.Instant.now;


/**
 * Created by User on 06.6.2018.
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    private final long interval;
    private final Storage storage;
   @Autowired
    public TransactionServiceImpl(@Value("${interval.milliseconds}") long interval,Storage storage) {
        this.interval = interval;
        this.storage=storage;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        long currentMillis = now().toEpochMilli();
        if (isWithinLastMinute(currentMillis, transaction)) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            statistics.accept(transaction.getAmount());
            storage.persist(currentMillis, new Timing(transaction.getTimestamp(), statistics));
            return true;
        }
        return false;
    }

    private boolean isWithinLastMinute(long current, Transaction transaction) {
        long time = (current - transaction.getTimestamp());
        return time < interval && time >= 0;
    }
}
