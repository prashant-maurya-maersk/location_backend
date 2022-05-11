package com.maersk.ops.location.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "DST")
public class DaylightSavingTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String code;
	
	private String name;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timezone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dst")
	List<DstDisplacement> dstDisplacements;

}
