package com.weppapp_be.teuta_qendresa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Long capacity;
}
