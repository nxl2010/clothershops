package com.example.backend.service.impl;

import com.example.backend.dto.request.ShipInformationDTO;
import com.example.backend.enity.ShipInformation;
import com.example.backend.exception.ShipInformationException;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.ShipInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShipInformationService {
    @Autowired
    private ShipInformationRepository shipInformationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RandomStringService randomStringService;
    public void save(ShipInformationDTO shipInformationDTO, String code) {
        ShipInformation shipInformation = new ShipInformation();
        System.out.println(shipInformationDTO.getName());
        shipInformation.setName(shipInformationDTO.getName());
        shipInformation.setAddress(shipInformationDTO.getAddress());
        shipInformation.setSdt(shipInformationDTO.getSdt());
        shipInformation.setCode(randomStringService.generateRandomString(5));
        shipInformation.setCustomer(customerRepository.findByUser_UserName(code));
        shipInformationRepository.save(shipInformation);
    }

    public List<ShipInformationDTO> findAll(String code){
        List<ShipInformation> shipInformations =  shipInformationRepository.findByCustomerCode(customerRepository.findByUser_UserName(code).getCode());
        if (shipInformations.size() != 0){
            List<ShipInformationDTO> shipInformationDTOS = new ArrayList<>();
            for (ShipInformation std : shipInformations){
                ShipInformationDTO shipInformationDTO = new ShipInformationDTO();
                shipInformationDTO.setName(std.getName());
                shipInformationDTO.setAddress(std.getAddress());
                shipInformationDTO.setSdt(std.getSdt());
                shipInformationDTOS.add(shipInformationDTO);
            }
            return shipInformationDTOS;
        }
        else {
            throw new ShipInformationException("Không tìm thấy giá trị nào");
        }
    }

    public ShipInformationDTO findByCode(String code) {
        ShipInformationDTO shipInformationDTO = new ShipInformationDTO();

        Optional<ShipInformation> shipInformation = shipInformationRepository.findByCode(code);
        if (shipInformation.isPresent()){
            ShipInformation ship = shipInformation.get();
            shipInformationDTO.setName(ship.getName());
            shipInformationDTO.setAddress(ship.getAddress());
            shipInformationDTO.setSdt(ship.getSdt());
            return shipInformationDTO;
        }else {
            throw new ShipInformationException("NONE");

        }
    }

    public ShipInformation findLast(String code) {
       Optional<ShipInformation> shipInformation = shipInformationRepository.findTopByCustomerCodeOrderByIdDesc(customerRepository.findByUser_UserName(code).getCode());
       if (shipInformation.isPresent()){
           return shipInformation.get();
       }else {
           throw new ShipInformationException("None");
       }

    }
}
