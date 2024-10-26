package com.example.demo.sevice;

import com.example.demo.dto.ServiceDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.exception.ExceptionSpec;
import com.example.demo.exception.LogicalException;
import com.example.demo.mapper.ServiceMapper;
import com.example.demo.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;

    public List<ServiceDto> getAllServices() {
        return serviceMapper.mapToDtoList(serviceRepository.findAll());
    }

    public ServiceDto createService(ServiceDto dto) {
        ServiceEntity entity = serviceMapper.mapToEntity(dto);
        entity.setActive(false);
        return serviceMapper.mapToDto(serviceRepository.save(entity));
    }

    public ServiceDto updateService(Long id, ServiceDto dto) {
        ServiceEntity existingEntity = getServiceEntityById(id);

        existingEntity.setName(dto.getName());
        existingEntity.setCost(dto.getCost());
        existingEntity.setMaxUsageLimit(dto.getMaxUsageLimit());
        existingEntity.setActive(dto.isActive());
        return serviceMapper.mapToDto( serviceRepository.save(existingEntity));
    }

    public void deleteService(Long id) {
        ServiceEntity existingEntity = getServiceEntityById(id);
        serviceRepository.delete(existingEntity);
    }

    private ServiceEntity getServiceEntityById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.SERVICE_NOT_FOUND));
    }

    public List<ServiceDto> getActiveServices() {
        return serviceMapper.mapToDtoList(serviceRepository.findActiveServices());
    }
}
