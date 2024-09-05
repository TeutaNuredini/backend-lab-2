package com.weppapp_be.teuta_qendresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private String address;
    private String city;
    private String state;
}
