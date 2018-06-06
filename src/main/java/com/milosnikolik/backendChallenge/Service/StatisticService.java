package com.milosnikolik.backendChallenge.Service;

import com.milosnikolik.backendChallenge.Model.Statistics;
import org.springframework.stereotype.Service;

/**
 * Created by User on 06.6.2018.
 */
@Service
public interface StatisticService {
    Statistics getStatistics();
}
