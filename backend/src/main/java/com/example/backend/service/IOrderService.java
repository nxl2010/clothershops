package com.example.backend.service;

import com.example.backend.dto.request.OrderDTO;
import com.example.backend.enity.OrderShip;

import java.util.List;

public interface IOrderService {
    //Lưu đơn hàng mới
    String save(OrderDTO orderDTO, String code);
    //Cập nhật giá
    void updatePay(boolean isPaid, String code);
    //Lấy đơn hàng cho người dùng
    <T> List<T> findAllByCustomerCode(String code);
    <T> List<T> findBYCustomerCodeAndOrderShip(String code, OrderShip orderShip);
    //Lấy đơn hàng cho admin
    <T> List<T> findAllForAdmin();
    //Cập nhật vạn chuỷen
    void updateShip(OrderShip orderShip, String code);

}
