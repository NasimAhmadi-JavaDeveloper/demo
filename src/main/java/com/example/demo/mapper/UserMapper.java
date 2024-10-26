package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "permissions", target = "permissionIds")
    UserDto mapToDto(UserEntity userEntity);

    UserEntity mapToEntity(UserDto.Insert userDto);

    default Set<Long> mapPermissionsToIds(Set<ServiceEntity> permissions) {
        return permissions.stream()
                .map(ServiceEntity::getId)
                .collect(Collectors.toSet());
    }

   UserDto.Credit mapToCredit(UserEntity entity);
}
