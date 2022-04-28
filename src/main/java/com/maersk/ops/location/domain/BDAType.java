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
@Table(name = "BDA_TYPE")
public class BDAType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String type;
	private String code;
	private String owner;
	private String bdaGroupType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bdaType")
	private List<ContentType> contentTypes;

}
