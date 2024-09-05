package com.weppapp_be.teuta_qendresa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
