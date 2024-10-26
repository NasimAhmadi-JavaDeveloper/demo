package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@Entity
@Accessors(chain = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(nullable = false)
    private BigDecimal credit;
    @ManyToMany
    @JoinTable(name = "user_permission_service",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceEntity> permissions;
    public enum UserType {
        SIMPLE, ADMIN
    }
}

