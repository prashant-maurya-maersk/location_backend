package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.StateDomain;

@Service
public interface StateService {
	
	public StateDomain createState(StateDomain domainState);
	
	public StateDomain updateState(StateDomain domainState);

}
