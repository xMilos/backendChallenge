package com.milosnikolik.backendChallenge.Controller;

import com.milosnikolik.backendChallenge.Model.Statistics;
import com.milosnikolik.backendChallenge.Service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 06.6.2018.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    StatisticService statisticService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity statistics() {
        Statistics transactionStatistic = statisticService.getStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(transactionStatistic);
    }

}
