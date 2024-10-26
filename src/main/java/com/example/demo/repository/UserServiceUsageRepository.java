package com.example.demo.repository;

import com.example.demo.entity.UserServiceUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceUsageRepository extends JpaRepository<UserServiceUsageEntity, Long> {

}
