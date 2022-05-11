package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timezone")
public class Timezone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long rowid;
	
	private String code;
	private String name;
	private String offsetMin;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "timezone")
	private DaylightSavingTime dst;
	
}
