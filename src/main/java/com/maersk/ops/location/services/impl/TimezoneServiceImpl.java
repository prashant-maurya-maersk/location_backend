package com.maersk.ops.location.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.DST;
import com.maersk.ops.location.domain.Displacement;
import com.maersk.ops.location.domain.OlsonDomain;
import com.maersk.ops.location.domain.TimezoneDomain;
import com.maersk.ops.location.model.DaylightSavingTime;
import com.maersk.ops.location.model.DstDisplacement;
import com.maersk.ops.location.model.OlsonTimezone;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.OlsonTimeRepo;
import com.maersk.ops.location.repository.TimezoneRepo;
import com.maersk.ops.location.services.TimezoneService;

@Service
public class TimezoneServiceImpl implements TimezoneService {
	
	@Autowired
	private TimezoneRepo timezoneRepo;
	
	@Autowired
	private OlsonTimeRepo olsonRepo;
	
	@Override
	public TimezoneDomain createTimezone(TimezoneDomain timezoneDomain) {
		Timezone timezone = timezoneMapper(timezoneDomain, new Timezone());
		timezone = timezoneRepo.save(timezone);
		timezoneDomain.setRowid(timezone.getRowid());
		return timezoneDomain;
	}
	
	@Override
	public TimezoneDomain updateTimezone(TimezoneDomain timezoneDomain) {
		Optional<Timezone> timezoneDB = null;
		try {
			timezoneDB = timezoneRepo.findById(timezoneDomain.getRowid());
		}catch(Exception e) {
			e.getMessage();
		}
		if(timezoneDB.isPresent()) {
			Timezone timezone = timezoneMapper(timezoneDomain, timezoneDB.get());
			timezone = timezoneRepo.save(timezone);
			timezoneDomain.setRowid(timezone.getRowid());
		}
		return timezoneDomain;
	}
	
	private Timezone timezoneMapper(TimezoneDomain timeDomain, Timezone timezone) {
		timezone = Timezone.builder().code(timeDomain.getCode()).description(timeDomain.getDescription())
				.name(timeDomain.getName()).offsetMin(timeDomain.getOffsetMin())
				.rowid(timeDomain.getRowid()==null?null:timeDomain.getRowid()).build();
		DaylightSavingTime dst = dstMapper(timeDomain.getDst(), timezone);
		timezone.setDst(dst);
		return timezone;
	}
	
	private DaylightSavingTime dstMapper(DST dstDomain, Timezone time) {
		DaylightSavingTime dst = DaylightSavingTime.builder().code(dstDomain.getCode()).description(dstDomain.getDescription())
				.name(dstDomain.getName()).rowid(dstDomain.getRowid()==null?null:dstDomain.getRowid())
				.build();
		
		if(dstDomain.getDstDisplacements()!=null && dstDomain.getDstDisplacements().size()>0) {
			List<DstDisplacement> dstDisplacementList = displacementMapper(dstDomain.getDstDisplacements(), dst);
			dst.setDstDisplacements(dstDisplacementList);
		}
		dst.setTimezone(time);
		return dst;
	}
	
	private List<DstDisplacement> displacementMapper(List<Displacement> domainDisplacementList, DaylightSavingTime dst) {
		List<DstDisplacement> dstDisplacementList = new ArrayList<DstDisplacement>();
		for(Displacement displacement: domainDisplacementList) {
			dstDisplacementList.add(DstDisplacement.builder().description(displacement.getDescription())
					.dst(dst).endTime(displacement.getEndTime()).startTime(displacement.getStartTime())
					.timeDifference(displacement.getTimeDifference()).year(displacement.getYear())
					.rowid(displacement.getRowid()==null?null:displacement.getRowid()).build());
		}
		return dstDisplacementList;
	}
	
	@Override
	public OlsonDomain createOlsonTimezone(OlsonDomain domainOlson) {
		OlsonTimezone olson = olsonMapper(domainOlson, new OlsonTimezone());
		olson = olsonRepo.save(olson);
		domainOlson.setRowid(olson.getRowid());
		return domainOlson;
	}
	
	@Override
	public OlsonDomain updateOlsonTimezone(OlsonDomain domainOlson) {
		Optional<OlsonTimezone> olsonDB = null;
		try {
			olsonDB = olsonRepo.findById(domainOlson.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(olsonDB.isPresent()) {
			OlsonTimezone olson = olsonMapper(domainOlson, olsonDB.get());
			olson = olsonRepo.save(olson);
			domainOlson.setRowid(olson.getRowid());
		}
		return domainOlson;
	}
	
	
	private OlsonTimezone olsonMapper(OlsonDomain domainOlson, OlsonTimezone olson) {
		if(domainOlson != null) {
			return olson.builder().description(domainOlson.getDescription()).name(domainOlson.getName())
					.rowid(domainOlson.getRowid()==null?null:domainOlson.getRowid()).build();
		}
		return null;
	}
	
}
