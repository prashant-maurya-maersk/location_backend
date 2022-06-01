package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityOpeningHours {
	 private String  Day;
	 private String  OpenTimeHours;
	 private String  OpenTimeMinutes;
	 private String  CloseTimeHours;
	 private String  CloseTimeMinutes;
}
