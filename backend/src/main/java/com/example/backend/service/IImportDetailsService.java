package com.example.backend.service;

import com.example.backend.dto.request.ImportDetailsDTO;
import com.example.backend.enity.ImportDetails;

public interface IImportDetailsService {

    void save(ImportDetailsDTO importDetailsDTO);

    ImportDetails findAll();
}
