package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ALT_NAME")
public class AlternateName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	
	private String description;
	
	private String status;
	
	private String entityId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entityTypeId",referencedColumnName = "rowid")
	private EntityType entityTypeId;
}
