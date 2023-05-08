package com.domariev.identityservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleAuthority {

    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    COURIER("COURIER"),
    MANAGER("MANAGER"),
    COOK("COOK");

    private final String name;

}
