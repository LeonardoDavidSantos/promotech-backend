package com.promotech.api.controllers;

import com.promotech.api.domain.store.StoreRequestDTO;
import com.promotech.api.domain.store.StoreUpdateDTO;
import com.promotech.api.services.StoreService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/list-all")
    ResponseEntity<Object> listAll() {
        return ResponseEntity.ok(storeService.listAll());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Object> create(StoreRequestDTO dto) {
        return ResponseEntity.ok(storeService.create(dto));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Object> create(StoreUpdateDTO dto, @PathVariable(name = "id") @NotBlank UUID id) {
        return ResponseEntity.ok(storeService.update(dto, id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Object> delete(@PathVariable(name = "id") @NotBlank UUID id) {
        storeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
