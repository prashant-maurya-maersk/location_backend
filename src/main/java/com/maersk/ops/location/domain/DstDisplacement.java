package com.maersk.ops.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DST_Displacement")
public class DstDisplacement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String startTime;
	
	private String endTime;
	
	private String timeDifference;
	
	@ManyToOne
	@JoinColumn(name = "dst_rowid", nullable = false)
	private DaylightSavingTime dst;

}
