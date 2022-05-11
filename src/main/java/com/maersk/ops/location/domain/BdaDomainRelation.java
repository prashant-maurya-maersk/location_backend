package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.BDA;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BdaDomainRelation {
	private Long rowid;
	private BDA bda;

}
