package com.yez.planner.repository;

import com.yez.planner.vo.PlanMaster;
import com.yez.planner.vo.PlanMasterPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanMaster, PlanMasterPK> {

}
