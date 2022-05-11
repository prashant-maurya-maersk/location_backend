package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.EntityType;

@Repository
public interface EntityTypeRepo extends CrudRepository<EntityType, Long> {
	public List<EntityType> findByEntityName(String entityName);
}
