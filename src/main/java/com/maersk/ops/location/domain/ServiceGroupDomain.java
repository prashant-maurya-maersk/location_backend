package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceGroupDomain {
    private Long rowid;
	private String name; 
	private String description; 
	private String code; 
	private String classificationCode; 
	private String isActive; 
}
