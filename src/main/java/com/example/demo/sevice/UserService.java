package com.example.demo.sevice;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ExceptionSpec;
import com.example.demo.exception.LogicalException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ServiceJunction serviceJunction;

    public UserDto grantServicePermission(Long userId, Long serviceId) {
        UserEntity userEntity = getUserEntityById(userId);
        ServiceEntity serviceEntity = serviceJunction.getServiceEntityById(serviceId);

        if (UserEntity.UserType.SIMPLE.equals(userEntity.getUserType())) {
            userEntity.getPermissions().add(serviceEntity);
            return userMapper.mapToDto(userRepository.save(userEntity));
        } else {
            throw new LogicalException(ExceptionSpec.USER_SHOULD_BE_SIMPLE);
        }
    }

    public UserDto revokeServicePermission(Long userId, Long serviceId) {
        UserEntity userEntity = getUserEntityById(userId);
        ServiceEntity serviceEntity = serviceJunction.getServiceEntityById(serviceId);

        if (UserEntity.UserType.SIMPLE.equals(userEntity.getUserType())) {
            userEntity.getPermissions().remove(serviceEntity);
            return userMapper.mapToDto(userRepository.save(userEntity));
        } else {
            throw new LogicalException(ExceptionSpec.USER_SHOULD_BE_SIMPLE);
        }
    }

    public UserDto createUser(UserDto.Insert dto) {
        UserEntity userEntity = userMapper.mapToEntity(dto);
        return userMapper.mapToDto(userRepository.save(userEntity));
    }

    public void deleteUser(Long id) {
        UserEntity existingEntity = getUserEntityById(id);
        userRepository.delete(existingEntity);
    }

    public UserDto.Credit allocateCredit(Long userId, BigDecimal amount) {
        UserEntity userEntity = getUserEntityById(userId);

        if (UserEntity.UserType.SIMPLE.equals(userEntity.getUserType())) {
            userEntity.setCredit(userEntity.getCredit().add(amount));
            return userMapper.mapToCredit(userRepository.save(userEntity));
        } else {
            throw new LogicalException(ExceptionSpec.CREDIT_ERROR);
        }
    }

    public UserDto updateUser(UserDto.Update dto) {
        UserEntity existingEntity = getUserEntityById(dto.getId());
        existingEntity.setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setUserType(dto.getUserType())
                .setCredit(dto.getCredit());
        return userMapper.mapToDto(userRepository.save(existingEntity));
    }

    public UserEntity getUserEntityById(Long dto) {
        return userRepository.findById(dto)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public void saveUserEntity(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Set<UserDto.Permission> getAuthorizedServices(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NAME_NOT_FOUND_EXCEPTION));

        Set<ServiceEntity> permissions = user.getPermissions()
                .stream()
                .filter(ServiceEntity::isActive)
                .collect(Collectors.toSet());

       return userMapper.mapToPermissionDto(permissions);
    }
}
