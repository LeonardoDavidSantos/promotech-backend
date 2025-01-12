package com.promotech.api.controllers;

import com.promotech.api.domain.category.Category;
import com.promotech.api.domain.category.CategoryRequestDTO;
import com.promotech.api.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity getAllCategories() {
        var allCategories = categoryRepository.findAll();
        return ResponseEntity.ok(allCategories);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.name());
        category.setDescription(categoryRequestDto.description());
        category.setTag(categoryRequestDto.tag());

        return ResponseEntity.ok(categoryRepository.save(category));
    }
}
