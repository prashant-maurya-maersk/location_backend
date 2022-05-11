package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.domain.SiteDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.Site;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.SiteRepo;
import com.maersk.ops.location.services.GeoServices;
import com.maersk.ops.location.services.SiteService;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteRepo siteRepo;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private GeoServices geoServices;

	@Override
	public SiteDomain createSite(SiteDomain domainSite) {
		Site site = siteMapper(domainSite, new Site());
		site = siteRepo.save(site);
		EntityType entityType = entityRepo.findByEntityName("SITE").get(0);
		if(domainSite.getAlternateNames()!=null && domainSite.getAlternateNames().size()>0) {
			List<AlternateNameDomain> altNameDomainList = geoServices.createAltName(domainSite.getAlternateNames(), site.getRowid().toString(), entityType);
			domainSite.setAlternateNames(altNameDomainList);
		}
		if(domainSite.getAlternateCodes()!=null && domainSite.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainSite.getAlternateCodes(), site.getRowid().toString(), entityType);
			domainSite.setAlternateCodes(altCodeDomainList);
		}
		if(domainSite.getParentDetails()!=null && domainSite.getParentDetails().size()>0) {
			List<ParentDetail> parentList = geoServices.createParents(domainSite.getParentDetails(), site.getRowid().toString(), entityType);
			domainSite.setParentDetails(parentList);
		}
		if(domainSite.getBdaDetails()!=null && domainSite.getBdaDetails().size()>0) {
			List<BdaDomainRelation> bdaRelation = geoServices.createBdaRelation(domainSite.getBdaDetails(), site.getRowid().toString(), entityType);
			domainSite.setBdaDetails(bdaRelation);
		}
		domainSite.setRowid(site.getRowid());
		return domainSite;
	}
	
	@Override
	public SiteDomain updateSite(SiteDomain domainSite) {
		Optional<Site> siteDB = null;
		try {
			siteDB = siteRepo.findById(domainSite.getRowid());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(siteDB.isPresent()) {
			Site updatedSite = siteMapper(domainSite, siteDB.get());
			siteRepo.save(updatedSite);
			EntityType entityType = entityRepo.findByEntityName("SITE").get(0);
			if(domainSite.getAlternateNames()!=null && domainSite.getAlternateNames().size()>0) {
				List<AlternateNameDomain> altNameDomainList = geoServices.updateAltName(domainSite.getAlternateNames(), updatedSite.getRowid().toString(), entityType);
				domainSite.setAlternateNames(altNameDomainList);
			}
			if(domainSite.getAlternateCodes()!=null && domainSite.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = geoServices.updateAltCode(domainSite.getAlternateCodes(), updatedSite.getRowid().toString(), entityType);
				domainSite.setAlternateCodes(altCodeDomainList);
			}
			if(domainSite.getParentDetails()!=null && domainSite.getParentDetails().size()>0) {
				List<ParentDetail> parentList = geoServices.updateParents(domainSite.getParentDetails(), updatedSite.getRowid().toString(), entityType);
				domainSite.setParentDetails(parentList);
			}
			if(domainSite.getBdaDetails()!=null && domainSite.getBdaDetails().size()>0) {
				List<BdaDomainRelation> bdaRelation = geoServices.updateBdaRelation(domainSite.getBdaDetails(), updatedSite.getRowid().toString(), entityType);
				domainSite.setBdaDetails(bdaRelation);
			}
			domainSite.setRowid(updatedSite.getRowid());
		}
		return domainSite;
	}
	
	private Site siteMapper(SiteDomain domainSite, Site site) {
		if(domainSite !=null) {
			return site.builder().addressLine1(domainSite.getAddressLine1()).addressLine2(domainSite.getAddressLine2())
					.addressLine3(domainSite.getAddressLine3()).description(domainSite.getDescription()).gpsFlag(domainSite.getGpsFlag())
					.gsmFlag(domainSite.getGsmFlag()).latitude(domainSite.getLatitude()).longitude(domainSite.getLongitude()).name(domainSite.getName())
					.postalCode(domainSite.getPostalCode()).rowIdObject(domainSite.getRowIdObject()).siteType(domainSite.getSiteType())
					.status(domainSite.getStatus()).street(domainSite.getStreet()).validFrom(domainSite.getValidFrom())
					.validTo(domainSite.getValidTo()).workaroundReason(domainSite.getWorkaroundReason())
					.rowid(domainSite.getRowid()==null?null:domainSite.getRowid()).build();
		}
		return null;
	}
}
