package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.AlternateCode;
import com.maersk.ops.location.model.EntityType;

@Repository
public interface AlternateCodeRepo extends JpaRepository<AlternateCode, Long> {
	List<AlternateCode> findByEntityIdAndEntityTypeId(String entityId, EntityType entityTypeId);
}
