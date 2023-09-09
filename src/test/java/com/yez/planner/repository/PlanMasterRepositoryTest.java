package com.yez.planner.repository;

import com.yez.planner.enums.Weather;
import com.yez.planner.vo.PlanMaster;
import com.yez.planner.vo.PlanMasterPK;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanMasterRepositoryTest {

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
            PlanMaster planMaster = PlanMaster.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(planMaster);

            // when
            planRepository.findById(new PlanMasterPK(userId, date)).ifPresent(searchedPlanMaster -> {
                // then
                assertEquals(planMaster.getUserId(), searchedPlanMaster.getUserId());
                assertEquals(planMaster.getDate(), searchedPlanMaster.getDate());
                assertEquals(planMaster.getWeather(), searchedPlanMaster.getWeather());
                assertNotNull(searchedPlanMaster.getCreateDt());
                assertNull(planMaster.getModifyDt());
            });
        }

        @Test
        @Order(2)
        @DisplayName("Non-Nullable 필드만 입력된 경우")
        void successCreateTest2() {
            // given
            String userId = RandomString.make(20);
            String date = "20230822";
            PlanMaster planMaster = PlanMaster.builder().userId(userId).date(date).build();
            planRepository.save(planMaster);

            // when
            planRepository.findById(new PlanMasterPK(userId, date)).ifPresent(searchedPlanMaster -> {
                // then
                assertEquals(planMaster.getUserId(), searchedPlanMaster.getUserId());
                assertEquals(planMaster.getDate(), searchedPlanMaster.getDate());
                assertNotNull(searchedPlanMaster.getCreateDt());
                assertNull(planMaster.getModifyDt());
            });
        }

        @Test
        @Order(3)
        @DisplayName("Non-Nullable 필드(date)가 누락된 경우")
        void failureCreateTest1() {
            // given
            String userId = RandomString.make(20);
            // when then
            assertThrows(NullPointerException.class, () -> PlanMaster.builder().userId(userId).build());
        }

        @Test
        @Order(4)
        @DisplayName("Non-Nullable 필드(userId)가 누락된 경우")
        void failureCreateTest2() {
            // given
            String date = "20230822";
            // when then
            assertThrows(NullPointerException.class, () -> PlanMaster.builder().date(date).build());
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
            PlanMaster planMaster = PlanMaster.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(planMaster);

            // when
            String newWeather = Weather.SUNNY.name();
            planRepository.findById(new PlanMasterPK(userId, date)).ifPresent(searchedPlanMaster -> {
                searchedPlanMaster.modifyWeather(newWeather);
                planRepository.save(searchedPlanMaster);
            });

            // then
            planRepository.findById(new PlanMasterPK(userId, date)).ifPresent(searchedPlanMaster -> {
                assertEquals(planMaster.getUserId(), searchedPlanMaster.getUserId());
                assertEquals(planMaster.getDate(), searchedPlanMaster.getDate());
                assertNotEquals(weather, searchedPlanMaster.getWeather());
                assertEquals(newWeather, searchedPlanMaster.getWeather());
                assertNotNull(searchedPlanMaster.getModifyDt());
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
            PlanMaster planMaster = PlanMaster.builder().userId(userId).date(date).weather(weather).build();
            planRepository.save(planMaster);

            // when
            planRepository.findById(new PlanMasterPK(userId, date)).ifPresent(searchedPlanMaster -> planRepository.delete(searchedPlanMaster));

            // then
            assertFalse(planRepository.findById(new PlanMasterPK(userId, date)).isPresent());
        }
    }
}