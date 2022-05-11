package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.CodeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternateCodeDomain {
	
	private Long rowid;

	private CodeType codetype;
	
	private String code;
}
