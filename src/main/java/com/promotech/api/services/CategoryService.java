package com.promotech.api.services;

import com.promotech.api.domain.category.Category;
import com.promotech.api.domain.category.CategoryRequestDTO;
import com.promotech.api.domain.category.CategoryResponseDTO;
import com.promotech.api.mappers.CategoryMapper;
import com.promotech.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    public void create(CategoryRequestDTO dto) {
        Category category = mapper.toEntity(dto);
        categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> listAll() {
        return categoryRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<Category> getById(UUID id) {
        return categoryRepository.findById(id);
    }
}
