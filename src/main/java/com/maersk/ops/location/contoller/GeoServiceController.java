package com.maersk.ops.location.contoller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.BdaDomain;
import com.maersk.ops.location.domain.BdaTypeDomain;
import com.maersk.ops.location.domain.CodeTypeDomain;
import com.maersk.ops.location.domain.EntityTypeDomain;
import com.maersk.ops.location.domain.OlsonDomain;
import com.maersk.ops.location.domain.TimezoneDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.services.GeoServices;
import com.maersk.ops.location.services.TimezoneService;

@RestController
public class GeoServiceController {
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private TimezoneService timezoneService;
	
	@Autowired
	private GeoServices geoServices;
	
	@PostMapping("/create/TimeZone")
	public TimezoneDomain createTimezone(@RequestBody TimezoneDomain time) {
		return timezoneService.createTimezone(time);
	}
	
	@PostMapping("update/Timezone")
	public TimezoneDomain updateTimezone(@RequestBody TimezoneDomain time) {
		return timezoneService.updateTimezone(time);
	}
	
	@PostMapping("/create/Olson")
	public OlsonDomain createOlson(@RequestBody OlsonDomain olson) {
		return timezoneService.createOlsonTimezone(olson);
	}
	
	@PostMapping("/update/Olson")
	public OlsonDomain updateOlson(@RequestBody OlsonDomain olson) {
		return timezoneService.updateOlsonTimezone(olson);
	}
	
	@PostMapping("/create/EntityType")
	public EntityType createEntity(@RequestBody EntityTypeDomain entityType) {
		EntityType entity = EntityType.builder().entityName(entityType.getEntityName())
				.rowid(entityType.getRowid()==null?null:entityType.getRowid()).build();
		return entityRepo.save(entity);
	}
	
	@PostMapping("/update/EntityType")
	public EntityType updateEntity(@RequestBody EntityTypeDomain entityType) {
		Optional<EntityType> entityTypeDB = null;
		try {
			entityTypeDB = entityRepo.findById(entityType.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(entityTypeDB.isPresent()) {
			EntityType updatedEntity = entityTypeDB.get().builder().entityName(entityType.getEntityName())
					.rowid(entityType.getRowid()==null?null:entityType.getRowid()).build();
			updatedEntity = entityRepo.save(updatedEntity);
			entityType.setRowid(updatedEntity.getRowid());
		}
		return null;
	}
	
	@PostMapping("/create/CodeType")
	public CodeTypeDomain createCodeType(@RequestBody CodeTypeDomain domainCodeType) {
		return geoServices.createCodeType(domainCodeType);
	}
	
	@PostMapping("/update/CodeType")
	public CodeTypeDomain updateCodeType(@RequestBody CodeTypeDomain domainCodeType) {
		return geoServices.updateCodeType(domainCodeType);
	}
	
	@PostMapping("/create/BDA")
	public BdaDomain createBda(@RequestBody BdaDomain bdaDomain) {
		return geoServices.createBDA(bdaDomain);
	}
	
	@PostMapping("/update/BDA")
	public BdaDomain updateBda(@RequestBody BdaDomain bdaDomain) {
		return geoServices.updateBDA(bdaDomain);
	}
	
	@PostMapping("/create/BdaType")
	public BdaTypeDomain createBdaType(@RequestBody BdaTypeDomain bdaType) {
		return geoServices.createBdaType(bdaType);
	}
	
	@PostMapping("/update/BdaType")
	public BdaTypeDomain updateBdaType(@RequestBody BdaTypeDomain bdaType) {
		return geoServices.updateBdaType(bdaType);
	}

}
