package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.PostalCodeDomain;

@Service
public interface PostalCodeService {
	
	public PostalCodeDomain createPostalCode(PostalCodeDomain domainPostal);
	public PostalCodeDomain updatePostalCode(PostalCodeDomain domainPostal);
}
