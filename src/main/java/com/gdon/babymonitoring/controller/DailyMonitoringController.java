/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdon.babymonitoring.controller;

import com.gdon.babymonitoring.model.DailyMonitor;
import com.gdon.babymonitoring.service.DailyMonitoringService;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gduvinage
 */
@RestController
@RequestMapping("/dailymonitoring")
public class DailyMonitoringController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyMonitoringController.class);

    @Autowired
    DailyMonitoringService dailyMonitoringService;

    @RequestMapping(method = GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<DailyMonitor> list() {
        return dailyMonitoringService.getAll();
    }

    @RequestMapping(method = POST)
    public void dailyMonitor(@RequestParam(value = "entryDay", required = true) final Date entryDay,
            @RequestParam(value = "amountTaken", required = true) final int amountTaken,
            @RequestParam(value = "comment", required = false) final String comment,
            @RequestParam(value = "amountPrepared", required = true) final int amountPrepared,
            @RequestParam(value = "entryHour", required = true) final Date entryHour) {

    }
}
