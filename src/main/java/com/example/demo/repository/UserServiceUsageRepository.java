package com.example.demo.repository;

import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserServiceUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceUsageRepository extends JpaRepository<UserServiceUsageEntity, Long> {
    Optional<UserServiceUsageEntity> findByUserAndService(UserEntity user, ServiceEntity service);
}
