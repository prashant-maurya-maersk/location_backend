package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "BDA")
public class BDA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bdaType_id",referencedColumnName = "rowid")
	private BDAType bdaType;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bda")
//	private List<AlternateCode> alternateCodes; 

}
