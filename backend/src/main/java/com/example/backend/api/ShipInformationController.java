package com.example.backend.api;

import com.example.backend.dto.request.ShipInformationDTO;
import com.example.backend.service.impl.ShipInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/shipinfor")
public class ShipInformationController {
    @Autowired
    private ShipInformationService shipInformationService;
    @PostMapping
    public ResponseEntity<?> addShipInfor(@RequestBody ShipInformationDTO shipInformationDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        shipInformationService.save(shipInformationDTO, authentication.getName());
        return ResponseEntity.ok("ok");
    }
    @GetMapping
    public ResponseEntity<?> findAllByCustomerCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return new ResponseEntity<>( shipInformationService.findAll(authentication.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/last")
    public ResponseEntity<?> findLastByCustomerCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return new ResponseEntity<>( shipInformationService.findLast(authentication.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }



}
