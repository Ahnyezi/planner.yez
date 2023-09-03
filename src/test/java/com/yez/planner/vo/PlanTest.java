package com.yez.planner.vo;

import com.yez.planner.enums.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlanTest {

    @Test
    void createTest() {
        String userId = "anyeji1220";
        String date = "20230822";
        String weather = Weather.SUNNY.name();

        Plan plan = Plan.builder()
                .userId(userId)
                .date(date)
                .weather(weather)
                .build();

        Assertions.assertEquals(plan.getUserId(), userId);
        Assertions.assertEquals(plan.getDate(), date);
        Assertions.assertEquals(plan.getWeather(), weather);
    }
}