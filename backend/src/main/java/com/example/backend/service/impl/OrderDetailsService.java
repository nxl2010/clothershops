package com.example.backend.service.impl;

import com.example.backend.dto.request.OrderdetailsDTO;
import com.example.backend.enity.Order;
import com.example.backend.enity.OrderDetails;
import com.example.backend.repository.OrderDetailsRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    public OrderDetails addOrderDetails(){
        return null;
    }

    public void save(OrderdetailsDTO orderdetailsDTO, Order order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setSize(sizeRepository.findByName(orderdetailsDTO.getSize()));
        orderDetails.setProduct(productRepository.findByCode(orderdetailsDTO.getProductCode()));
        orderDetails.setQuantity(orderdetailsDTO.getQuantity());
        orderDetails.setPrice(orderdetailsDTO.getPrice());
        orderDetails.setOrder(order);
        orderDetailsRepository.save(orderDetails);
    }
}
