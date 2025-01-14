package com.promotech.api.controllers;

import com.promotech.api.domain.coupon.CouponRequestDTO;
import com.promotech.api.domain.user.User;
import com.promotech.api.services.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    ResponseEntity<Object> create(Authentication authentication, @RequestBody @Valid CouponRequestDTO dto) {
        User user = (User) authentication.getPrincipal();
        couponService.create(dto, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Object> findAll(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(couponService.listAll(user));
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<Object> delete(Authentication authentication, @PathVariable UUID id) {
        User user = (User) authentication.getPrincipal();
        boolean deleted = couponService.delete(id, user);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
