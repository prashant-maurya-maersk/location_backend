package com.maersk.ops.location.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DST {
	private Long rowid;
	private String code;
	private String name;
	private String description;
	private List<Displacement> dstDisplacements;

}
