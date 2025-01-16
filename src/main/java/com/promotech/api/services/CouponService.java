package com.promotech.api.services;

import com.promotech.api.domain.coupon.Coupon;
import com.promotech.api.domain.coupon.dto.CouponRequestDTO;
import com.promotech.api.domain.coupon.dto.CouponResponseDTO;
import com.promotech.api.domain.coupon.dto.CouponUpdateDTO;
import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.user.User;
import com.promotech.api.mappers.CouponMapper;
import com.promotech.api.repositories.CouponRepository;
import com.promotech.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private UserRepository userRepository;

    private StoreService storeService;

    @Autowired
    private CouponMapper mapper;

    public Optional<CouponResponseDTO> create(CouponRequestDTO dto, User user) {
        Optional<Store> store = storeService.getById(dto.store_id());

        if (store.isEmpty()) {
            return Optional.empty();
        }
        Coupon coupon = mapper.toEntity(dto);
        coupon.setUser(user);
        coupon.setStore(store.get());
        coupon.setIsExpired(false);

        return Optional.ofNullable(mapper.toDto(couponRepository.save(coupon)));
    }

    public void delete(UUID couponId, User user) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();

        if (this.belongsToUser(user, coupon) || user.isAdmin()) {
            couponRepository.deleteById(couponId);
        }
    }

    public Optional<CouponResponseDTO> update(CouponUpdateDTO dto, UUID couponId, User user) {
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        Optional<Store> store = storeService.getById(dto.store_id());

        if (store.isEmpty() || coupon.isEmpty() || !this.belongsToUser(user, coupon.get())) {
            return Optional.empty();
        }

        Coupon dbCoupon = coupon.get();
        mapper.updateEntityFromDto(dto, dbCoupon);
        dbCoupon.setStore(store.get());

        return Optional.ofNullable(mapper.toDto(couponRepository.save(dbCoupon)));
    }

    public List<CouponResponseDTO> listAll() {
        return couponRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<CouponResponseDTO> listFromUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return couponRepository.findByUser(user).stream().map(mapper::toDto).toList();
    }

    private boolean belongsToUser(User user, Coupon coupon) {
        UUID couponUserId = coupon.getUser().getId();
        return user.getId().equals(couponUserId);
    }
}
