package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
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

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Insert {
        @NotEmpty(message = "username is required")
        private String username;
        @NotEmpty(message = "password is required")
        private String password;
        private BigDecimal credit;
        private UserEntity.UserType userType;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private Long id;
        @NotEmpty(message = "username is required")
        private String username;
        @NotEmpty(message = "password is required")
        private String password;
        private BigDecimal credit;
        private UserEntity.UserType userType;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Credit {
        private Long id;
        private String username;
        private BigDecimal credit;
    }
}
