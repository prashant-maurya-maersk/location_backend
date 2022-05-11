package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Displacement {
	
	private Long rowid;
	
    private String startTime;
	
	private String endTime;
	
	private String timeDifference;
	
	private String description;
	
	private String year;

}
