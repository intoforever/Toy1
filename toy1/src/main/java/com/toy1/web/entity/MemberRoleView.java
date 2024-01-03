package com.toy1.web.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class MemberRoleView {
	
	@EmbeddedId
	private PrimaryKeys primaryKey;
	private String roleName;
}
