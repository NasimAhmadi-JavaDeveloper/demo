package com.example.demo.repository;

import com.example.demo.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    @Query(value = "SELECT * FROM service_entity WHERE is_active = true", nativeQuery = true)
    List<ServiceEntity> findActiveServices();
}