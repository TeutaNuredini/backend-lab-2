package com.weppapp_be.teuta_qendresa.dto;


import com.weppapp_be.teuta_qendresa.entity.Role;

public record AuthenticationResponse(String token, String refreshToken, Role role) {
}
