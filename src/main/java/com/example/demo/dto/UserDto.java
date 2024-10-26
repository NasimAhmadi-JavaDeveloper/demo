package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private BigDecimal credit;
    private UserEntity.UserType userType;
    private Set<Long> permissionIds;
}
