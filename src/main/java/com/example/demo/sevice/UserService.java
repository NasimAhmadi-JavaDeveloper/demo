package com.example.demo.sevice;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ExceptionSpec;
import com.example.demo.exception.LogicalException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    public UserDto grantServicePermission(Long userId, Long serviceId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));

        ServiceEntity serviceEntity = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.SERVICE_NOT_FOUND));

        if (userEntity.getUserType() == UserEntity.UserType.SIMPLE) {
            userEntity.getPermissions().add(serviceEntity);
            return userMapper.mapToDto(userRepository.save(userEntity));
        } else {
            throw new LogicalException(ExceptionSpec.PERMISSION_ERROR);
        }
    }
}
