package com.hybrid.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTaskFixedRate {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTaskFixedRate.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 500000) //Thuộc tính fixedRate dùng để chỉ định một khoảng thời gian cố định mà công việc sẽ được thực hiện định kỳ.
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
