package com.example.backend.service.impl;

import com.example.backend.dto.request.CartDTO;
import com.example.backend.dto.response.CartResponseDTO;
import com.example.backend.enity.Cart;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;

    public void save(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setProduct(productRepository.findByCode(cartDTO.getProductCode()));
        cart.setSize(sizeRepository.findByName(cartDTO.getSize()));
        cart.setQuantity(cartDTO.getQuantity());
        cartRepository.save(cart);
    }

    public List<CartResponseDTO> findAllByCustomer(String name) {
        List<Cart> carts = cartRepository.findByCustomer_Name(name);
        List<CartResponseDTO> cartDTOS = new ArrayList<>();
        for (Cart std: carts){
            CartResponseDTO cartDTO = new CartResponseDTO();
            cartDTO.setId(std.getId());
            cartDTO.setProductName(std.getProduct().getName());
            cartDTO.setSize(std.getSize().getName());
            cartDTO.setQuantity(std.getQuantity());
            cartDTOS.add(cartDTO);
        }
        return cartDTOS;
    }
    public List<CartResponseDTO> findById(List<Long> ids){
       List<CartResponseDTO> cartResponseDTOS = new ArrayList<>();
        for (Long std: ids){
            Optional<Cart> cartOptional = cartRepository.findById(std);
            if (cartOptional.isPresent()){
                Cart cart = cartOptional.get();
                CartResponseDTO cartDTO = new CartResponseDTO();
                cartDTO.setId(cart.getId());
                cartDTO.setProductName(cart.getProduct().getName());
                cartDTO.setSize(cart.getSize().getName());
                cartDTO.setQuantity(cart.getQuantity());
               cartResponseDTOS.add(cartDTO);
            }
        }
        return cartResponseDTOS;
    }
}
