package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.FacilityType;

@Repository
public interface FacilityTypeRepo extends JpaRepository<FacilityType, Long> {

}
