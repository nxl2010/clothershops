package com.example.backend.service;

import com.example.backend.dto.response.ProductColor;
import com.example.backend.enity.SuggestClother;
import com.example.backend.exception.CategoryNotFoundException;
import com.example.backend.repository.SuggestClotherRepository;
import com.example.backend.service.impl.CategoryService;
import com.example.backend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestClotherService {

    @Autowired
    private SuggestClotherRepository suggestClotherRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    public List<SuggestClother> getAllSuggestClothers() {
        return suggestClotherRepository.findAll();
    }

    public Optional<SuggestClother> getSuggestClotherById(Long id) {
        return suggestClotherRepository.findById(id);
    }

    public SuggestClother saveSuggestClother(SuggestClother suggestClother) {
        return suggestClotherRepository.save(suggestClother);
    }
    public List<String> getListSubCategory(String category){
        Optional<SuggestClother> suggestClothers = suggestClotherRepository.findByMainCategory(category);
        if (suggestClothers.isPresent()){
            return suggestClothers.get().getSubCategory();
        }
        else {
            return new ArrayList<>();
        }
    }

    public void deleteSuggestClother(Long id) {
        suggestClotherRepository.deleteById(id);
    }

    public List<ProductColor> suggestToCategoryCode(String categoryCode) {
        Optional<SuggestClother> suggestClother =suggestClotherRepository.findByMainCategory(categoryCode);
        List<ProductColor> productColorList = new ArrayList<>();

        if (suggestClother.isPresent()){
           List<String> subColother = suggestClother.get().getSubCategory();
           for (String std : subColother){
               ProductColor productColor = new ProductColor();
               productColor.setCategory(categoryService.findByCode(std));
               productColor.setProducts(productService.findOfCategoryColor(std));
               productColorList.add(productColor);
           }
           return productColorList;
        }
        else {
            throw new CategoryNotFoundException(categoryCode);
        }
    }
}
