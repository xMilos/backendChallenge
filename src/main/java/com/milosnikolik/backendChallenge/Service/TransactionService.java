package com.milosnikolik.backendChallenge.Service;

import com.milosnikolik.backendChallenge.Model.Transaction;
import org.springframework.stereotype.Service;

/**
 * Created by User on 06.6.2018.
 */
@Service
public interface TransactionService {
    public boolean addTransaction(Transaction transaction);
}
