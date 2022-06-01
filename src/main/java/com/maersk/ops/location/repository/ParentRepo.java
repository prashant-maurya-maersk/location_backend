package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.ParentDetailRelation;

@Repository
public interface ParentRepo extends CrudRepository<ParentDetailRelation, Long> {
	List<ParentDetailRelation> findByChildIdAndChildType(String childId, EntityType childType);
}
