package com.example.backend.service.impl;

import com.example.backend.dto.request.CartDTO;
import com.example.backend.dto.response.CartResponseDTO;
import com.example.backend.enity.Cart;
import com.example.backend.enity.Customer;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(CartDTO cartDTO, String code) {
        Cart cart = new Cart();
        cart.setCustomer(customerRepository.findByCode(code));
        cart.setProduct(productRepository.findByCode(cartDTO.getProductCode()));
        cart.setSize(sizeRepository.findByName(cartDTO.getSize()));
        cart.setQuantity(cartDTO.getQuantity());
        cartRepository.save(cart);
    }

    public List<CartResponseDTO> findAllByCustomer(String name) {
        Customer customer = customerRepository.findByUser_UserName(name);
        List<Cart> carts = cartRepository.findByCustomerCode(customer.getCode());
        List<CartResponseDTO> cartDTOS = new ArrayList<>();
        for (Cart std: carts){
            CartResponseDTO cartDTO = new CartResponseDTO();
            cartDTO.setProductName(std.getProduct().getName());
            cartDTO.setSize(std.getSize().getName());
            cartDTO.setQuantity(std.getQuantity());
            List<Double> prices = std.getProduct().getPrices();
            cartDTO.setPrice(prices.get(prices.size() - 1));
            cartDTOS.add(cartDTO);
        }
        return cartDTOS;
    }
//    public List<CartResponseDTO> findById(List<Long> ids){
//       List<CartResponseDTO> cartResponseDTOS = new ArrayList<>();
//        for (Long std: ids){
//            Optional<Cart> cartOptional = cartRepository.findById(std);
//            if (cartOptional.isPresent()){
//                Cart cart = cartOptional.get();
//                CartResponseDTO cartDTO = new CartResponseDTO();
//                cartDTO.setProductName(cart.getProduct().getName());
//                cartDTO.setSize(cart.getSize().getName());
//                cartDTO.setQuantity(cart.getQuantity());
//                cartDTO.setPrice(cart.getProduct().getPrices().get(0));
//               cartResponseDTOS.add(cartDTO);
//            }
//        }
//        return cartResponseDTOS;
//    }
}
