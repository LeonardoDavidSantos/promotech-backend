package com.promotech.api.services;

import com.promotech.api.domain.category.Category;
import com.promotech.api.domain.promotion.Promotion;
import com.promotech.api.domain.promotion.dto.PromotionRequestDTO;
import com.promotech.api.domain.promotion.dto.PromotionResponseDTO;
import com.promotech.api.domain.promotion.dto.PromotionUpdateDTO;
import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.user.User;
import com.promotech.api.mappers.PromotionMapper;
import com.promotech.api.repositories.PromotionRepository;
import com.promotech.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StoreService storeService;

    @Autowired
    private PromotionMapper mapper;

    public Optional<PromotionResponseDTO> create(PromotionRequestDTO dto, User user) {
        Optional<Category> category = categoryService.getById(dto.category_id());
        Optional<Store> store = storeService.getById(dto.store_id());

        if (category.isEmpty() || store.isEmpty()) {
            throw new IllegalArgumentException("Invalid store or category");
        }
        Promotion promotion = mapper.toEntity(dto);
        promotion.setUser(user);
        promotion.setCategory(category.get());
        promotion.setStore(store.get());
        promotion.setIsExpired(false);

        return Optional.ofNullable(mapper.toDto(promotionRepository.save(promotion)));
    }

    public void delete(UUID promotionId, User user) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();

        if (this.belongsToUser(user, promotion) || user.isAdmin()) {
            promotionRepository.deleteById(promotionId);
        }
    }

    public Optional<PromotionResponseDTO> update(PromotionUpdateDTO dto, UUID promotionId, User user) throws IllegalArgumentException, IllegalAccessException
    {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);
        Optional<Category> category = categoryService.getById(dto.category_id());
        Optional<Store> store = storeService.getById(dto.store_id());

        if (promotion.isEmpty() || category.isEmpty() || store.isEmpty()) {
            throw new IllegalArgumentException("Invalid promotion, category or store");
        }
        if (!this.belongsToUser(user, promotion.get())) {
            throw new IllegalAccessException("Promotion don't belongs to user");
        }

        Promotion dbPromotion = promotion.get();
        mapper.updateEntityFromDto(dto, dbPromotion);

        dbPromotion.setCategory(category.get());
        dbPromotion.setStore(store.get());

        return Optional.ofNullable(mapper.toDto(promotionRepository.save(dbPromotion)));
    }

    public List<PromotionResponseDTO> listAll() {
        return promotionRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<PromotionResponseDTO> listFromUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return promotionRepository.findByUser(user).stream().map(mapper::toDto).toList();
    }

    private boolean belongsToUser(User user, Promotion promotion) {
        UUID promotionUserId = promotion.getUser().getId();
        return user.getId().equals(promotionUserId);
    }
}
