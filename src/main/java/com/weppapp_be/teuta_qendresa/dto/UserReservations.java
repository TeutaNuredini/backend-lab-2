package com.weppapp_be.teuta_qendresa.dto;

import com.weppapp_be.teuta_qendresa.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReservations {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;
    private LocalDateTime reservationDate;
}
