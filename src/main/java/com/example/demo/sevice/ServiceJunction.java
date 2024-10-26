package com.example.demo.sevice;

import com.example.demo.entity.ServiceEntity;
import com.example.demo.exception.ExceptionSpec;
import com.example.demo.exception.LogicalException;
import com.example.demo.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceJunction {

    private final ServiceRepository serviceRepository;
    public ServiceEntity getServiceEntityById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.SERVICE_NOT_FOUND));
    }
}
