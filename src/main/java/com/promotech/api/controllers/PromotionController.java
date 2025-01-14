package com.promotech.api.controllers;

import com.promotech.api.domain.promotion.PromotionRequestDTO;
import com.promotech.api.domain.promotion.PromotionUpdateDTO;
import com.promotech.api.domain.user.User;
import com.promotech.api.services.PromotionService;
import jakarta.validation.Valid;
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

    @GetMapping
    public ResponseEntity<Object> getAllPromotions() {
        return ResponseEntity.ok(promotionService.listAll());
    }

    @PostMapping
    public ResponseEntity<Object> createPromotion(Authentication auth, @RequestBody @Valid PromotionRequestDTO dto) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(promotionService.create(dto, user));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updatePromotion(Authentication auth, @RequestBody @Valid PromotionUpdateDTO dto, @PathVariable(name = "id") UUID id) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(promotionService.update(dto, id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePromotion(Authentication auth, @PathVariable(name = "id") UUID id) {
        User user = (User) auth.getPrincipal();
        promotionService.delete(id, user);
        return ResponseEntity.ok().build();
    }
}
