package com.promotech.api.services;

import com.promotech.api.domain.coupon.Coupon;
import com.promotech.api.domain.coupon.CouponRequestDTO;
import com.promotech.api.domain.coupon.CouponResponseDTO;
import com.promotech.api.domain.user.User;
import com.promotech.api.domain.user.UserRole;
import com.promotech.api.mappers.CouponMapper;
import com.promotech.api.repositories.CouponRepository;
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
    private CouponMapper mapper;

    public void create(CouponRequestDTO dto, User user) {
        Coupon coupon = mapper.toEntity(dto);
        coupon.setUser(user);
        coupon.setIsExpired(false);
        couponRepository.save(coupon);
    }

    public List<CouponResponseDTO> listAll(User user) {
        return couponRepository.findByUser(user).stream().map(mapper::toDto).toList();
    }

    public boolean delete(UUID id, User user) {
        Optional<Coupon> coupon = couponRepository.findById(id);

        if (coupon.isEmpty()) {
            return false;
        }
        boolean couponOwnByUser = coupon.get().getUser().getId().equals(user.getId());
        boolean userIsAdmin = user.getRole().getRole().equals(UserRole.ADMIN.getRole());

        if (!couponOwnByUser && !userIsAdmin) {
            return false;
        }
        couponRepository.deleteById(id);
        return true;
    }
}
