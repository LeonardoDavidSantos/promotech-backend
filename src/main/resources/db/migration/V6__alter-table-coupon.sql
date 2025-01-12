ALTER TABLE coupon
ADD CONSTRAINT fk_coupon_store FOREIGN KEY (store_id) REFERENCES store(id),
ADD CONSTRAINT fk_coupon_user FOREIGN KEY (user_id) REFERENCES users(id);