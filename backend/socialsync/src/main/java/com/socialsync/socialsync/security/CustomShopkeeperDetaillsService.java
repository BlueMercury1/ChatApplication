package com.socialsync.socialsync.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialsync.socialsync.entity.ShopKeeper;
import com.socialsync.socialsync.repository.ShopKeeperRepository;

@Service
public class CustomShopkeeperDetaillsService implements UserDetailsService {

    @Autowired
    private ShopKeeperRepository shopKeeperRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ShopKeeper> shopKeeper = shopKeeperRepository.findByShopKeeperEmail(username);
        System.err.println(shopKeeper);
        return shopKeeper.get();
    }

}
