package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.CountryDomain;

@Service
public interface CountryService {

	public CountryDomain createCountry(CountryDomain domainCountry);
	public CountryDomain updateCountry(CountryDomain domainCountry);
}
