package com.yez.planner.vo;

import com.yez.planner.enums.Weather;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanMasterTest {

    String userId = "anyeji1220";
    String date = "20230822";
    String weather = Weather.SUNNY.name();

    @Test
    @Order(1)
    @DisplayName("Non-Nullable, Nullable 필드가 모두 입력된 경우")
    void successCreateTest() {
        PlanMaster planMaster = PlanMaster.builder()
                .userId(userId)
                .date(date)
                .weather(weather)
                .build();

        assertEquals(planMaster.getUserId(), userId);
        assertEquals(planMaster.getDate(), date);
        assertEquals(planMaster.getWeather(), weather);
    }

    @Test
    @Order(2)
    @DisplayName("Non-Nullable 필드(time)가 누락된 경우")
    void failureCreateTest() {
        assertThrows(NullPointerException.class, ()-> PlanMaster.builder()
                .userId(userId)
                .weather(weather)
                .build());
    }
}