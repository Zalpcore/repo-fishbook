package com.fishbook.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Column(name = "order_id", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_id;

	@Column(name = "renter_id", unique = true, nullable = false)
	private String renter_id;

	@Column(name = "service_id", unique = true, nullable = false)
	private String service_id;

	@Column(name = "begin_date", unique = true, nullable = false)
	private Date begin_date;

	@Column(name = "total_price", unique = true, nullable = false)
	private double total_price;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private User user;

	
	
}