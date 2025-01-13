package com.promotech.api.controllers;

import com.promotech.api.domain.coupon.Coupon;
import com.promotech.api.domain.coupon.CouponRequestDTO;
import com.promotech.api.domain.user.User;
import com.promotech.api.repositories.CouponRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;


    @PostMapping
    ResponseEntity<Object> create(Authentication authentication, @RequestBody @Valid CouponRequestDTO couponRequestDto) {
        User authUser = (User) authentication.getPrincipal();

        Coupon newCoupon = new Coupon();
        newCoupon.setUser(authUser);
        newCoupon.setTitle(couponRequestDto.title());
        newCoupon.setDescription(couponRequestDto.description());
        newCoupon.setCode(couponRequestDto.code());
        newCoupon.setLinkUrl(couponRequestDto.link_url());
        newCoupon.setIsExpired(false);

        return ResponseEntity.ok(couponRepository.save(newCoupon));
    }

    @GetMapping
    ResponseEntity<Object> findMany() {
        return ResponseEntity.ok(couponRepository.findAll());
    }
}
