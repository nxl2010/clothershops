package com.example.backend.service.impl;

import com.example.backend.enity.Customer;
import com.example.backend.enity.User;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomStringService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static class OrderDetailsService {
    }

    @Service
    public static class CustomerService {
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private RandomStringService randomStringService;
        public void save(String userName){
            User user = userRepository.findByUserName(userName);
            Customer customer = new Customer();
            customer.setCode(randomStringService.generateRandomString(6));
            customer.setUser(user);
            customerRepository.save(customer);
        }
    }
}
