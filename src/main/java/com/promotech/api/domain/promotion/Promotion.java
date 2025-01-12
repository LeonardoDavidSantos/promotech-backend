package com.promotech.api.domain.promotion;

import com.promotech.api.domain.category.Category;
import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.user.User;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
