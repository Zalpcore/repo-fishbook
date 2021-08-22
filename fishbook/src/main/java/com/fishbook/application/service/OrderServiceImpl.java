package com.fishbook.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fishbook.application.model.Order;
import com.fishbook.application.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		this.orderRepository.save(order);

	}

	@Override
	public Order getOrderById(int id) {
		Optional<Order> optional = orderRepository.findById(id);
		Order order = null;
		if (optional.isPresent()) {
			order = optional.get();
		} else {
			throw new RuntimeException("Order not found for id: " + id);
		}
		return order;
	}

	@Override
	public void deleteOrderById(int id) {
		this.orderRepository.deleteById(id);

	}

}
