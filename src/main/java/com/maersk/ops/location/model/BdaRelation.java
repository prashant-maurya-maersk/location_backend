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
@Table(name = "BDA_REL")
public class BdaRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bda_id",referencedColumnName = "rowid")
	private BDA bda;
	
    private String entityId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entityTypeId",referencedColumnName = "rowid")
	private EntityType entityTypeId;
}
