/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gdon.babymonitoring.dao;

import com.gdon.babymonitoring.model.DailyMonitor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gduvinage
 */

public interface DailyMonitoringRepository {
   
    public List<DailyMonitor> getDailyMonitorings();
}
