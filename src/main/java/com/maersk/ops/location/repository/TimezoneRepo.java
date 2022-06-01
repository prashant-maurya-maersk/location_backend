package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.Timezone;

@Repository
public interface TimezoneRepo extends JpaRepository<Timezone, Long> {

}
