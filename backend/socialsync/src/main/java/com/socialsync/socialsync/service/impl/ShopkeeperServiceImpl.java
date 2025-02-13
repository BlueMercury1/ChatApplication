package com.socialsync.socialsync.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialsync.socialsync.dto.ShopKeeperDto;
import com.socialsync.socialsync.entity.ShopKeeper;
import com.socialsync.socialsync.exceptions.ShopKeeperNotFoundException;
import com.socialsync.socialsync.repository.ShopKeeperRepository;
import com.socialsync.socialsync.service.ShopkeeperService;

@Service
public class ShopkeeperServiceImpl implements ShopkeeperService {

    @Autowired
    private ShopKeeperRepository keeperRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ShopKeeper addShopKeeper(ShopKeeperDto dto) {
        ShopKeeper keeper = new ShopKeeper();

        keeper.setShopKeeperId(UUID.randomUUID().toString());
        keeper.setShopKeeperPassword(passwordEncoder.encode(dto.getShopKeeperPassword()));
        keeper.setShopKeeperCity(dto.getShopKeeperCity());
        keeper.setShopKeeperEmail(dto.getShopKeeperEmail());
        keeper.setShopKeeperName(dto.getShopKeeperName());

        return keeperRepository.save(keeper);
    }

    @Override
    public ShopKeeper getShopKeeperById(String id) {
        return keeperRepository.findById(id).orElseThrow(() -> new ShopKeeperNotFoundException("ShopKeeper not found"));
    }

    @Override
    public List<ShopKeeper> getAllShopKeeper() {
        return keeperRepository.findAll();
    }

    @Override
    public ShopKeeper getShopKeeperByEmail(String email) {
        return keeperRepository.findById(email)
                .orElseThrow(() -> new ShopKeeperNotFoundException("ShopKeeper not found with " + email));
    }

}
