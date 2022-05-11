package com.maersk.ops.location.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "POSTAL_CODE")
public class PostalCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String postalcode;
	private String status;
	private String description;
	private String validFrom;
	private String validTo;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "postalCode")
//	private List<AlternateCode> alternateCodes;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "postalCode")
//	private List<BDA> bdaDetails;
	

}
