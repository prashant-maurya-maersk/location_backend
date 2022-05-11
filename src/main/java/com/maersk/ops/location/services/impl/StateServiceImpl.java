package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.domain.StateDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.State;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.StateRepository;
import com.maersk.ops.location.repository.TimezoneRepo;
import com.maersk.ops.location.services.GeoServices;
import com.maersk.ops.location.services.StateService;

@Service
public class StateServiceImpl implements StateService {
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private TimezoneRepo timeRepo;
	
	@Override
	public StateDomain createState(StateDomain domainState) {
		State state = stateMapper(domainState, new State());
		state = stateRepo.save(state);
		domainState.setRowid(state.getRowid());
		EntityType entityType = entityRepo.findByEntityName("STATE").get(0);
		if(domainState.getAlternateNames()!=null && domainState.getAlternateNames().size()>0) {
			List<AlternateNameDomain> domainAltNameList = geoServices.createAltName(domainState.getAlternateNames(), state.getRowid().toString(), entityType);
			domainState.setAlternateNames(domainAltNameList);
		}
		if(domainState.getAlternateCodes()!=null && domainState.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> domainAltCodeList = geoServices.createAltCode(domainState.getAlternateCodes(), state.getRowid().toString(), entityType);
			domainState.setAlternateCodes(domainAltCodeList);
		}
		if(domainState.getParentDetails()!=null && domainState.getParentDetails().size()>0) {
			List<ParentDetail> domainParentList = geoServices.createParents(domainState.getParentDetails(), state.getRowid().toString(), entityType);
			domainState.setParentDetails(domainParentList);
		}
		if(domainState.getBdaDetails()!=null && domainState.getBdaDetails().size()>0) {
			List<BdaDomainRelation> domainBdaRelations = geoServices.createBdaRelation(domainState.getBdaDetails(), state.getRowid().toString(), entityType);
			domainState.setBdaDetails(domainBdaRelations);
		}
		return domainState;
	}
	
	@Override
	public StateDomain updateState(StateDomain domainState) {
		Optional<State> stateDB = null;
		try {
			stateDB = stateRepo.findById(domainState.getRowid());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(stateDB.isPresent()) {
			State updatedState = stateMapper(domainState, stateDB.get());
			stateRepo.save(updatedState);
			domainState.setRowid(updatedState.getRowid());
			EntityType entityType = entityRepo.findByEntityName("STATE").get(0);
			if(domainState.getAlternateNames()!=null && domainState.getAlternateNames().size()>0) {
				List<AlternateNameDomain> domainAltNameList = geoServices.updateAltName(domainState.getAlternateNames(), updatedState.getRowid().toString(), entityType);
				domainState.setAlternateNames(domainAltNameList);
			}
			if(domainState.getAlternateCodes()!=null && domainState.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> domainAltCodeList = geoServices.updateAltCode(domainState.getAlternateCodes(), updatedState.getRowid().toString(), entityType);
				domainState.setAlternateCodes(domainAltCodeList);
			}
			if(domainState.getParentDetails()!=null && domainState.getParentDetails().size()>0) {
				List<ParentDetail> domainParentList = geoServices.updateParents(domainState.getParentDetails(), updatedState.getRowid().toString(), entityType);
				domainState.setParentDetails(domainParentList);
			}
			if(domainState.getBdaDetails()!=null && domainState.getBdaDetails().size()>0) {
				List<BdaDomainRelation> domainBdaRelations = geoServices.updateBdaRelation(domainState.getBdaDetails(), updatedState.getRowid().toString(), entityType);
				domainState.setBdaDetails(domainBdaRelations);
			}
		}
		return domainState;
	}
	
	private State stateMapper(StateDomain domainState, State state) {
		if(domainState!=null) {
			Optional<Timezone> timeDB = timeRepo.findById(domainState.getTimeZone().getRowid());
			if(timeDB.isPresent()) {
				return state.builder().description(domainState.getDescription()).name(domainState.getName())
						.status(domainState.getStatus()).timeZone(timeDB.get()).validFrom(domainState.getValidFrom())
						.validTo(domainState.getValidTo()).workaroundReason(domainState.getWorkaroundReason())
						.rowid(domainState.getRowid()==null?null:domainState.getRowid()).build();
			}	
		}
		return null;
	}
}
