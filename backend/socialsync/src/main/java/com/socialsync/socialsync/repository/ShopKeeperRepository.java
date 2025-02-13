package com.socialsync.socialsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialsync.socialsync.entity.ShopKeeper;

@Repository
public interface ShopKeeperRepository extends JpaRepository<ShopKeeper, String> {
    ShopKeeper findByShopKeeperEmail(String shopKeeperMail);
}
