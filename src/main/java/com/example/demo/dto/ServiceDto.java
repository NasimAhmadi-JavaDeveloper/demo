package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    @NotEmpty(message = "name is required")
    private String name;
    private double cost;
    private int maxUsageLimit;
    private boolean isActive;
}