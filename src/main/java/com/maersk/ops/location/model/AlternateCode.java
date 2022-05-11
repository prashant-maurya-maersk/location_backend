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
@Table(name = "ALT_CODE")
public class AlternateCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@ManyToOne
	@JoinColumn(name = "codetype_id",referencedColumnName = "rowid")
	private CodeType codetype;
	
	private String code;
	
    private String entityId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entityTypeId",referencedColumnName = "rowid")
	private EntityType entityTypeId;
	
}
