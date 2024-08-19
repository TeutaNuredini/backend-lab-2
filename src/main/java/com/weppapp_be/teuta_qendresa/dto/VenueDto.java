package com.weppapp_be.teuta_qendresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Long capacity;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
