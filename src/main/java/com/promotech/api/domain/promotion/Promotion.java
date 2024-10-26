package com.promotech.api.domain.promotion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "promotion")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private Float price;
    private String imgUrl;
    private String linkUrl;

    private Boolean isExpired;
    private Date createdAt;
    private Date updatedAt;
}
