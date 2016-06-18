/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdon.babymonitoring.service;

import com.gdon.babymonitoring.dao.DailyMonitoringRepositoryImpl;
import com.gdon.babymonitoring.model.DailyMonitor;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gduvinage
 */
@Service
public class DailyMonitoringService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyMonitoringService.class);

    @Autowired
    private DailyMonitoringRepositoryImpl dailyMonitoringRepositoryImpl;

    public void push(final Date entryDay, final int amountTaken, final String comment, final int amountPrepared, final Date entryHour) {
        final DailyMonitor dailyMonitoring = new DailyMonitor(entryDay, amountTaken, comment, amountPrepared, entryHour);
        dailyMonitoringRepositoryImpl.getDailyMonitorings().add(dailyMonitoring);
    }

    public void push(final Date entryDay, final int amountTaken, final int amountPrepared, final Date entryHour) {
        final DailyMonitor dailyMonitoring = new DailyMonitor(entryDay, amountTaken, amountPrepared, entryHour);
        dailyMonitoringRepositoryImpl.getDailyMonitorings().add(dailyMonitoring);
    }

    public List<DailyMonitor> getAll() {
        return dailyMonitoringRepositoryImpl.getDailyMonitorings();
    }

}
