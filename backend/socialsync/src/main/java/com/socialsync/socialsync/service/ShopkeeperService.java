package com.socialsync.socialsync.service;

import java.util.List;

import com.socialsync.socialsync.dto.ShopKeeperDto;
import com.socialsync.socialsync.entity.ShopKeeper;

public interface ShopkeeperService {

    ShopKeeper addShopKeeper(ShopKeeperDto dto);

    ShopKeeper getShopKeeperById(String id);

    ShopKeeper getShopKeeperByEmail(String email);

    List<ShopKeeper> getAllShopKeeper();

}
