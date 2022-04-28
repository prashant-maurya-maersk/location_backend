package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
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
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "postalCode")
	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "postalCode")
	private List<BusinessDefinedArea> bdaDetails;
	

}
