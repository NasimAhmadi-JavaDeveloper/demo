package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.ServiceEntity;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "permissions", target = "permissionIds")
    UserDto mapToDto(UserEntity userEntity);

    @Mapping(source = "permissionIds", target = "permissions")
    UserEntity toEntity(UserDto userDto);

    default Set<Long> mapPermissionsToIds(Set<ServiceEntity> permissions) {
        return permissions.stream()
                .map(ServiceEntity::getId)
                .collect(Collectors.toSet());
    }

    default Set<ServiceEntity> mapIdsToPermissions(Set<Long> permissionIds) {
        if (permissionIds == null) {
            return new HashSet<>();
        }
        return permissionIds.stream()
                .map(id -> {
                    ServiceEntity serviceEntity = new ServiceEntity();
                    serviceEntity.setId(id);
                    return serviceEntity;
                })
                .collect(Collectors.toSet());
    }
}
