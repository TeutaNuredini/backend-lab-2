package com.weppapp_be.teuta_qendresa.dto.request;


import com.weppapp_be.teuta_qendresa.entity.Role;

public record UserRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {
}
