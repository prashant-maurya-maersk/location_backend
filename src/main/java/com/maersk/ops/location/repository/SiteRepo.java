package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.Site;

@Repository
public interface SiteRepo extends JpaRepository<Site, Long> {

}
