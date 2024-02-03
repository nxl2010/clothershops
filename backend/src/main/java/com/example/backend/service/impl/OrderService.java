package com.example.backend.service.impl;

import com.example.backend.dto.request.OrderDTO;
import com.example.backend.dto.request.OrderdetailsDTO;
import com.example.backend.enity.Order;
import com.example.backend.enity.OrderShip;
import com.example.backend.enity.OrderStatus;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RandomStringService randomStringService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public void save(OrderDTO orderDTO, String code) {
//        Lưu order
        Order order = new Order();
        order.setCustomer(customerRepository.findByCode(code));
        order.setStatus(OrderStatus.NOTPAID);
        order.setShipStatus(OrderShip.IN_PROGRESS);
        orderRepository.save(order);
//        Lưu order deatils
        List<OrderdetailsDTO> orderdetailsDTOS = orderDTO.getOrderdetailsDTOS();
        for (OrderdetailsDTO std : orderdetailsDTOS){
            orderDetailsService.save(std,order);
        }
//        Cập nhật thông tin sau khi lưu order
        order.setDiscount(orderDTO.getDiscount());
        order.setCode(randomStringService.generateRandomString(6));
        order.setBillingAddress(orderDTO.getBillingAddress());
        order.setPaymentMethod(order.getPaymentMethod());
        order.setTotalAmount(orderDTO.getTotalAmount());
        orderRepository.save(order);
    }
}
