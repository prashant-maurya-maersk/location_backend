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
@Table(name = "ALT_NAME")
public class AlternateName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	
	private String description;
	
	private String status;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "city_rowid", nullable = false)
	private City city;
}
