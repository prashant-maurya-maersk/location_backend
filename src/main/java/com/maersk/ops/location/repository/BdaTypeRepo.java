package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.BDAType;

@Repository
public interface BdaTypeRepo extends JpaRepository<BDAType, Long> {

}
