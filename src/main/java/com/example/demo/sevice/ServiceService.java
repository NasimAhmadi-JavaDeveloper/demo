package com.example.demo.sevice;

import com.example.demo.dto.ServiceDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserServiceUsageEntity;
import com.example.demo.exception.ExceptionSpec;
import com.example.demo.exception.LogicalException;
import com.example.demo.mapper.ServiceMapper;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserServiceUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceMapper serviceMapper;
    private final UserService userService;
    private final ServiceRepository serviceRepository;
    private final UserServiceUsageRepository userServiceUsageRepository;
    private final ServiceJunction serviceJunction;


    public List<ServiceDto> getAllServices() {
        return serviceMapper.mapToDtoList(serviceRepository.findAll());
    }

    public ServiceDto createService(ServiceDto dto) {
        ServiceEntity entity = serviceMapper.mapToEntity(dto);
        entity.setActive(false);
        return serviceMapper.mapToDto(serviceRepository.save(entity));
    }

    public ServiceDto updateService(Long id, ServiceDto dto) {
        ServiceEntity existingEntity = serviceJunction.getServiceEntityById(id);

        existingEntity.setName(dto.getName())
                .setCost(dto.getCost())
                .setMaxUsageLimit(dto.getMaxUsageLimit())
                .setActive(dto.isActive());
        return serviceMapper.mapToDto(serviceRepository.save(existingEntity));
    }

    public void deleteService(Long id) {
        ServiceEntity existingEntity = serviceJunction.getServiceEntityById(id);
        serviceRepository.delete(existingEntity);
    }

    public List<ServiceDto> getActiveServices() {
        return serviceMapper.mapToDtoList(serviceRepository.findActiveServices());
    }

    public void activateService(Long id, Long userId) {
        UserEntity userEntity = userService.getUserEntityById(userId);

        if (UserEntity.UserType.SIMPLE.equals(userEntity.getUserType())) {
            throw new LogicalException(ExceptionSpec.ADMIN_USER_CAN_ACTIVATE);
        }

        ServiceEntity serviceEntity = serviceJunction.getServiceEntityById(id);
        serviceEntity.setActive(true);
        serviceRepository.save(serviceEntity);
    }

    public void deactivateService(Long id, Long userId) {
        UserEntity userEntity = userService.getUserEntityById(userId);

        if (UserEntity.UserType.SIMPLE.equals(userEntity.getUserType())) {
            throw new LogicalException(ExceptionSpec.ADMIN_USER_CAN_DEACTIVATE);
        }

        ServiceEntity serviceEntity = serviceJunction.getServiceEntityById(id);
        serviceEntity.setActive(false);
        serviceRepository.save(serviceEntity);
    }

    public void useService(Long userId, Long serviceId) {
        UserEntity userEntity = userService.getUserEntityById(userId);
        ServiceEntity serviceEntity = serviceJunction.getServiceEntityById(serviceId);

        if (!serviceEntity.isActive()) {
            throw new LogicalException(ExceptionSpec.SERVICE_NOT_ACTIVE);
        }

        if (!userEntity.getPermissions().contains(serviceEntity)) {
            throw new LogicalException(ExceptionSpec.PERMISSION_DENIED);
        }

        BigDecimal serviceCost = BigDecimal.valueOf(serviceEntity.getCost());
        if (userEntity.getCredit().compareTo(serviceCost) < 0) {
            throw new LogicalException(ExceptionSpec.INSUFFICIENT_CREDIT);
        }

        UserServiceUsageEntity usageEntity =
                userServiceUsageRepository.findByUserAndService(userEntity, serviceEntity)
                .orElseGet(() -> new UserServiceUsageEntity()
                        .setUser(userEntity)
                        .setService(serviceEntity)
                        .setUsageCount(0));

        if (usageEntity.getUsageCount() >= serviceEntity.getMaxUsageLimit()) {
            throw new LogicalException(ExceptionSpec.MAX_USAGE_LIMIT_REACHED);
        }

        userEntity.setCredit(userEntity.getCredit().subtract(serviceCost));
        userService.saveUserEntity(userEntity);

        usageEntity.setUsageCount(usageEntity.getUsageCount() + 1);
        userServiceUsageRepository.save(usageEntity);
    }


}
