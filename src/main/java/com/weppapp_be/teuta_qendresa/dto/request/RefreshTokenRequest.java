package com.weppapp_be.teuta_qendresa.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(@NotNull String token) {
}
