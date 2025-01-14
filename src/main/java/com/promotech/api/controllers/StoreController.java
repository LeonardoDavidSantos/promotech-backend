package com.promotech.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("store")
public class StoreController {

    @GetMapping
    ResponseEntity<Object> listAll() {
        return ResponseEntity.ok().build();
    }
}
