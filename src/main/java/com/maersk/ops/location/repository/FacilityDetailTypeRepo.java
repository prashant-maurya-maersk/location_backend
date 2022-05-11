package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.FacilityDetailTypeRelation;

@Repository
public interface FacilityDetailTypeRepo extends JpaRepository<FacilityDetailTypeRelation, Long> {

}
