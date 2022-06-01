package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@Table(name = "PARENT_REL")
public class ParentDetailRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String childId;
	
	private String parentName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "childTypeId",referencedColumnName = "rowid")
	private EntityType childType;
	
	private String parentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentTypeId",referencedColumnName = "rowid")
	private EntityType parentType;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
}
