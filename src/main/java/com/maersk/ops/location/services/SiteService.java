package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.SiteDomain;

@Service
public interface SiteService {
	
	public SiteDomain createSite(SiteDomain domainSite);
	
	public SiteDomain updateSite(SiteDomain domainSite);

}
