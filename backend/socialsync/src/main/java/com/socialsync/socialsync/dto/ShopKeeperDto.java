package com.socialsync.socialsync.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopKeeperDto {

    private String shopKeeperId;

    private String shopKeeperName;

    private String shopKeeperEmail;

    private String shopKeeperPassword;

    private String shopKeeperCity;

    private Date createdAt;

    private Date updatedAt;
}
