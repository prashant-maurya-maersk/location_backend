package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.FacilityDetailTypeRelation;
import com.maersk.ops.location.model.OperationalFacilityDetail;

@Repository
public interface FacilityDetailTypeRepo extends JpaRepository<FacilityDetailTypeRelation, Long> {
	List<FacilityDetailTypeRelation> findByOperationalFacilityDetail(OperationalFacilityDetail operationalFacilityDetail);
}
