package com.example.backend.api;

import com.example.backend.dto.response.ProductColor;
import com.example.backend.enity.SuggestClother;
import com.example.backend.service.SuggestClotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/suggestclothers")
public class SuggestClotherController {

    @Autowired
    private SuggestClotherService suggestClotherService;

    @GetMapping
    public ResponseEntity<List<SuggestClother>> getAllSuggestClothers() {
        List<SuggestClother> suggestClothers = suggestClotherService.getAllSuggestClothers();
        return new ResponseEntity<>(suggestClothers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuggestClother> getSuggestClotherById(@PathVariable("id") Long id) {
        Optional<SuggestClother> suggestClother = suggestClotherService.getSuggestClotherById(id);
        return suggestClother.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SuggestClother> addSuggestClother(@RequestBody SuggestClother suggestClother) {
        SuggestClother newSuggestClother = suggestClotherService.saveSuggestClother(suggestClother);
        return new ResponseEntity<>(newSuggestClother, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SuggestClother> updateSuggestClother(@PathVariable("id") Long id, @RequestBody SuggestClother suggestClotherDetails) {
        Optional<SuggestClother> optionalSuggestClother = suggestClotherService.getSuggestClotherById(id);
        if (optionalSuggestClother.isPresent()) {
            SuggestClother suggestClother = optionalSuggestClother.get();
            suggestClother.setMainCategory(suggestClotherDetails.getMainCategory());
            suggestClother.setSubCategory(suggestClotherDetails.getSubCategory());
            SuggestClother updatedSuggestClother = suggestClotherService.saveSuggestClother(suggestClother);
            return new ResponseEntity<>(updatedSuggestClother, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestClother(@PathVariable("id") Long id) {
        suggestClotherService.deleteSuggestClother(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/suggest")
    public ResponseEntity<?> suggestToCategory(@RequestParam String categoryCode){
        List<ProductColor> productColor = suggestClotherService.suggestToCategoryCode(categoryCode);
        return new ResponseEntity<>(productColor,HttpStatus.OK);
    }
}