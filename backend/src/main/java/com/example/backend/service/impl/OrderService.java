package com.example.backend.service.impl;

import com.example.backend.dto.request.OrderDTO;
import com.example.backend.dto.request.OrderdetailsDTO;
import com.example.backend.dto.request.ShipInformationDTO;
import com.example.backend.dto.response.OrderRDTO;
import com.example.backend.enity.Order;
import com.example.backend.enity.OrderShip;
import com.example.backend.enity.OrderStatus;
import com.example.backend.exception.OrderException;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ShipInformationService shipInformationService;
    @Autowired
    private ProductSizeService productSizeService;
    @Override
    @Transactional
// Tạo mới đơn hàng
    public String save(OrderDTO orderDTO, String code) {
    //        Lưu order
        Order order = new Order();
        order.setCustomer(customerRepository.findByUser_UserName(code)  );
        orderRepository.save(order);

    //        Lưu order deatils
        List<OrderdetailsDTO> orderdetailsDTOS = orderDTO.getOrderdetailsDTOS();
        for (OrderdetailsDTO std : orderdetailsDTOS){
            if (!productSizeService.isInvento(std)){
                throw new ProductNotFoundException(std.getProductCode() + std.getSize());
            }
            productSizeService.reduceInveto(std);
            orderDetailsService.save(std,order);
        }

    //        Cập nhật thông tin sau khi lưu orderdetail
        String orderCode = randomStringService.generateRandomString(5);
        LocalDateTime localDateTime = LocalDateTime.now();
        ShipInformationDTO shipInformation = shipInformationService.findByCode(orderDTO.getShipCode());
        order.setName("Đơn hàng " + shipInformation.getName() + " " + orderCode);
        order.setCustomer_name(shipInformation.getName());
        order.setShippingAddress(shipInformation.getAddress());
        order.setSdt(shipInformation.getSdt());
        order.setPaymentMethod(order.isPaymentMethod());
        order.setStatus(isStatus(orderDTO.isPaymentMethod(), orderDTO.isPaid()));
        order.setCode(orderCode);
        order.setOrderDate(localDateTime);
        order.setShipStatus(OrderShip.CONFIRMING);
        order.setDiscount(order.getDiscount());
        order.setTotalAmount(orderDTO.getTotalAmount());
        orderRepository.save(order);
        return orderCode;

    }
    //Cap nhat trang thai thanh toán

    @Override
    @Transactional
    public void updatePay(boolean isPaid, String code) {
        Optional<Order> orderOptional = orderRepository.findByCode(code);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            if (isPaid){
                order.setStatus(OrderStatus.PAID);
            }
        }
    }

    @Override
    public List<OrderRDTO> findBYCustomerCodeAndOrderShip(String code, OrderShip orderShip) {
        List<Order> orderOptional = orderRepository.findByCustomerCodeAndShipStatus(customerRepository.findByUser_UserName(code).getCode(), orderShip);
        if (orderOptional.size() == 0){
           throw new OrderException("NUll");
        }
        else {
            return getOrderRDTOS(orderOptional);
        }

    }

    @Override
    public void updateShip(OrderShip orderShip, String code) {
        Optional<Order> orderOptional = orderRepository.findByCode(code);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setShipStatus(orderShip);

        }
    }

    @Override
    public List<OrderRDTO> findAllForAdmin() {
        List<Order> orderList = orderRepository.findAll();
        return getOrderRDTOS(orderList);
    }



    @Override
    public List<OrderRDTO> findAllByCustomerCode(String code) {
        List<Order> orderList = orderRepository.findByCustomerCode(customerRepository.findByUser_UserName(code).getCode());
        return getOrderRDTOS(orderList);
    }



//  Kiem tra phương thức vâận chuyển

    public OrderStatus isStatus(boolean paymentMethod, boolean isPaid){
        if(paymentMethod){
            return OrderStatus.OFFLINE;
        }
        else {
            if (isPaid){
                return OrderStatus.PAID;
            }
            else return OrderStatus.NOTPAID;
        }
    }
//    Chuyển Order sang orderRTo
    private List<OrderRDTO> getOrderRDTOS(List<Order> orderList) {
        List<OrderRDTO> orderRDTOS = new ArrayList<>();
        for (Order std : orderList){
            OrderRDTO orderRDTO = new OrderRDTO();
            orderRDTO.setName(std.getName());
            orderRDTO.setCustomerName(std.getCustomer_name());
            orderRDTO.setOrderStatus(std.getStatus());
            orderRDTO.setOrderShip(std.getShipStatus());
            orderRDTO.setTime(std.getOrderDate());
            orderRDTO.setTotalAmount(std.getTotalAmount());
            orderRDTO.setSdt(std.getSdt());
            orderRDTO.setShippingAddress(std.getShippingAddress());
            orderRDTOS.add(orderRDTO);
        }
        return orderRDTOS;
    }
    //Hoàn hàng


}
