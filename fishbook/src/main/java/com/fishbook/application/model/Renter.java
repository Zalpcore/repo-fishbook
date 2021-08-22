package com.fishbook.application.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "renters")
public class Renter {

	@Column(name = "renter_id", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer renter_id;
	
	@Column(name = "company_name", unique = true, nullable = false)
	private String company_name;
	
	@Column(name = "contact_name", unique = false, nullable = true)
	private String contact_name;
	
	@Column(name = "phone_1", unique = true, nullable = false)
	private String phone_1;
	
	@Column(name = "phone_2", unique = true, nullable = true)
	private String phone_2;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "address", unique = true, nullable = false)
	private String address;
	
	
	@Column(name = "create_TS", updatable = false, nullable = false)
	private Timestamp createTS;
		
	@Column(name = "update_TS", updatable = true, nullable = true)
	private Timestamp updateTS;

		
}