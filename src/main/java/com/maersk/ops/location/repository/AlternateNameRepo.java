package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.AlternateName;

@Repository
public interface AlternateNameRepo extends JpaRepository<AlternateName, Long> {

}
