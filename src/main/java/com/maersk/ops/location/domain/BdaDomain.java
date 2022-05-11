package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.BDAType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BdaDomain {
	private Long rowid;
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	
	private BDAType bdaType;

}
