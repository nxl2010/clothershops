package com.example.backend.service;

import com.example.backend.dto.request.OrderDTO;

public interface IOrderService {
    void save(OrderDTO orderDTO, String code);
}
