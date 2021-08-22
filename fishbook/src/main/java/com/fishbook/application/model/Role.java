package com.fishbook.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", length = 20)
	private ERole roleName;

	public Role() {}
	
	public Role(ERole roleName) {
		this.roleName = roleName;
		
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public ERole getRoleName() {
		return roleName;
	}

	public void setRoleName(ERole roleName) {
		this.roleName = roleName;
	}

	
	
	
	
	
	
	
	
}