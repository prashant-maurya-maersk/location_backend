package com.maersk.ops.location.domain;

import javax.persistence.CascadeType;
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
@Table(name = "CONTENT_TYPE")
public class ContentType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String contentType;
	private String validFrom;
	private String validTo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bdaType_id",referencedColumnName = "rowid")
	private BDAType bdaType;

}
