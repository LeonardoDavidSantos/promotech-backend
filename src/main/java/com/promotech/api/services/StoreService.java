package com.promotech.api.services;

import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.store.StoreRequestDTO;
import com.promotech.api.domain.store.StoreResponseDTO;
import com.promotech.api.domain.store.StoreUpdateDTO;
import com.promotech.api.domain.user.User;
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

    public Optional<StoreResponseDTO> create(StoreRequestDTO dto) {
        Store store = mapper.toEntity(dto);
        return Optional.ofNullable(mapper.toDto(storeRepository.save(store)));
    }

    public void delete(UUID storeId) {
        storeRepository.deleteById(storeId);
    }

    public Optional<StoreResponseDTO> update(StoreUpdateDTO dto, UUID storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isEmpty()) {
            return Optional.empty();
        }
        Store dbStore = store.get();
        mapper.updateEntityFromDto(dto, dbStore);

        return Optional.ofNullable(mapper.toDto(storeRepository.save(dbStore)));
    }

    public List<StoreResponseDTO> listAll() {
        return storeRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<Store> getById(UUID id) {
        return storeRepository.findById(id);
    }
}
