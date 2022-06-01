package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.AlternateName;
import com.maersk.ops.location.model.EntityType;

@Repository
public interface AlternateNameRepo extends JpaRepository<AlternateName, Long> {
	List<AlternateName> findByEntityIdAndEntityTypeId(String entityId, EntityType entityTypeId);
}
