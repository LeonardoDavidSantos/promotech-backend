package com.promotech.api.controllers;

import com.promotech.api.domain.promotion.dto.PromotionRequestDTO;
import com.promotech.api.domain.promotion.dto.PromotionUpdateDTO;
import com.promotech.api.domain.user.User;
import com.promotech.api.services.PromotionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/list-all")
    public ResponseEntity<Object> listAll() {
        return ResponseEntity.ok(promotionService.listAll());
    }

    @GetMapping("/list-from-user/{id}")
    public ResponseEntity<Object> listFromUser(@PathVariable(name = "id") @NotBlank UUID id) {
        return ResponseEntity.ok(promotionService.listFromUser(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(Authentication auth, @RequestBody @Valid PromotionRequestDTO dto) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(promotionService.create(dto, user));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> update(Authentication auth, @RequestBody @Valid PromotionUpdateDTO dto, @PathVariable(name = "id") @NotBlank UUID id) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(promotionService.update(dto, id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete (Authentication auth, @PathVariable(name = "id") @NotBlank UUID id) {
        User user = (User) auth.getPrincipal();
        promotionService.delete(id, user);
        return ResponseEntity.ok().build();
    }
}
