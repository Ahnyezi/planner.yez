package com.yez.planner.repository;

import com.yez.planner.vo.Plan;
import com.yez.planner.vo.PlanPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, PlanPK> {

}
