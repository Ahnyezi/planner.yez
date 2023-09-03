package com.yez.planner.repository;

import com.yez.planner.enums.Weather;
import com.yez.planner.vo.Plan;
import com.yez.planner.vo.PlanPK;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanRepositoryTest {

    @Autowired
    PlanRepository planRepository;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("INSERT 테스트")
    class forCreate {

        @Test
        @Order(1)
        @DisplayName("Non-Nullable, Nullable 필드가 모두 입력된 경우")
        void successCreateTest1() {
            // given
            String userId = RandomString.make(20);
            String date = "20230822";
            String weather = Weather.CLOUDY.name();
            Plan plan = Plan.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(plan);

            // when
            planRepository.findById(new PlanPK(userId, date)).ifPresent(searchedPlan -> {
                // then
                assertEquals(plan.getUserId(), searchedPlan.getUserId());
                assertEquals(plan.getDate(), searchedPlan.getDate());
                assertEquals(plan.getWeather(), searchedPlan.getWeather());
                assertNotNull(searchedPlan.getCreateDt());
                assertNull(plan.getModifyDt());
            });
        }

        @Test
        @Order(2)
        @DisplayName("Non-Nullable 필드만 입력된 경우")
        void successCreateTest2() {
            // given
            String userId = RandomString.make(20);
            String date = "20230822";
            Plan plan = Plan.builder().userId(userId).date(date).build();
            planRepository.save(plan);

            // when
            planRepository.findById(new PlanPK(userId, date)).ifPresent(searchedPlan -> {
                // then
                assertEquals(plan.getUserId(), searchedPlan.getUserId());
                assertEquals(plan.getDate(), searchedPlan.getDate());
                assertNotNull(searchedPlan.getCreateDt());
                assertNull(plan.getModifyDt());
            });
        }

        @Test
        @Order(3)
        @DisplayName("Non-Nullable 필드(date)가 누락된 경우")
        void failureCreateTest1() {
            // given
            String userId = RandomString.make(20);
            // when then
            assertThrows(NullPointerException.class, () -> Plan.builder().userId(userId).build());
        }

        @Test
        @Order(4)
        @DisplayName("Non-Nullable 필드(userId)가 누락된 경우")
        void failureCreateTest2() {
            // given
            String date = "20230822";
            // when then
            assertThrows(NullPointerException.class, () -> Plan.builder().date(date).build());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("UPDATE 테스트")
    class forModify {

        @Test
        @Order(1)
        @DisplayName("수정 가능한 필드(Weather)를 수정할 경우")
        void successModifyTest1() {
            // given
            String userId = RandomString.make(20);
            String date = "20230822";
            String weather = Weather.CLOUDY.name();
            Plan plan = Plan.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(plan);

            // when
            String newWeather = Weather.SUNNY.name();
            planRepository.findById(new PlanPK(userId, date)).ifPresent(searchedPlan -> {
                searchedPlan.modifyWeather(newWeather);
                planRepository.save(searchedPlan);
            });

            // then
            planRepository.findById(new PlanPK(userId, date)).ifPresent(searchedPlan -> {
                assertEquals(plan.getUserId(), searchedPlan.getUserId());
                assertEquals(plan.getDate(), searchedPlan.getDate());
                assertNotEquals(weather, searchedPlan.getWeather());
                assertEquals(newWeather, searchedPlan.getWeather());
                assertNotNull(searchedPlan.getModifyDt());
            });
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("DELETE 테스트")
    class forDelete {

        @Test
        @Order(1)
        @DisplayName("DB에 존재하는 Plan을 삭제하는 경우")
        void successDeleteTest1() {
            // given
            String userId = RandomString.make(20);
            String date = "20230822";
            String weather = Weather.CLOUDY.name();
            Plan plan = Plan.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(plan);

            // when
            planRepository.findById(new PlanPK(userId, date)).ifPresent(searchedPlan -> planRepository.delete(searchedPlan));

            // then
            assertFalse(planRepository.findById(new PlanPK(userId, date)).isPresent());
        }
    }
}