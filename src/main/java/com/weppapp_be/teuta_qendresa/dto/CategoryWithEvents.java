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
public class CategoryWithEvents {
    private Long id;
    private String name;
    private String description;
    private Long numberOfEvents;
    private LocalDateTime createdAt;
}
