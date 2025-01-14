package com.promotech.api.controllers;

import com.promotech.api.domain.category.CategoryRequestDTO;
import com.promotech.api.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public ResponseEntity getAllCategories(Authentication authentication) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDto) {
        return ResponseEntity.ok().build();
    }
}
