package com.promotech.api.domain.store;

import com.promotech.api.domain.coupon.Coupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "store")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
    private String imgUrl;
    private String linkUrl;
    private String tag;

    @OneToMany(mappedBy = "store")
    private List<Coupon> coupons;
}
