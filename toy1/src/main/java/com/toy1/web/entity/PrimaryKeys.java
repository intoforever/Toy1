package com.toy1.web.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PrimaryKeys implements Serializable {
	
	private Long memberId;
	private Integer roleId;

}
