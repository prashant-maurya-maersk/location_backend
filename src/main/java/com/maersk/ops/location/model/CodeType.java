package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CODE_TYPE")
public class CodeType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String typeCode;
	private String typeName;
	private String description;
	private String status;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}

}
