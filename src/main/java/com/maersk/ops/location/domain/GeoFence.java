package com.maersk.ops.location.domain;

import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@Table(name = "GEO_FENCE")
public class GeoFence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "site_id",referencedColumnName = "rowid")
	private Site site;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subarea_id",referencedColumnName = "rowid")
	private CitySubArea subarea;
}
