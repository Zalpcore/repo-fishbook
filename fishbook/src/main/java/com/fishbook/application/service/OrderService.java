package com.fishbook.application.service;

import java.util.List;

import com.fishbook.application.model.Order;

public interface OrderService {
	List<Order> getAllOrders();

	void saveOrder(Order order);

	Order getOrderById(int id);

	void deleteOrderById(int id);
}
