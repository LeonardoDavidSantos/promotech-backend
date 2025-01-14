package com.promotech.api.services;

import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.store.StoreResponseDTO;
import com.promotech.api.mappers.StoreMapper;
import com.promotech.api.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMapper mapper;

    public List<StoreResponseDTO> listAll() {
        return storeRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<Store> getById(UUID id) {
        return storeRepository.findById(id);
    }
}
