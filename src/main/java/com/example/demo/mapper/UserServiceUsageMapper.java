package com.example.demo.mapper;

import com.example.demo.dto.ServiceUsageReportDto;
import com.example.demo.entity.UserServiceUsageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserServiceUsageMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "usageCount", source = "usageCount")
    ServiceUsageReportDto toDto(UserServiceUsageEntity userServiceUsageEntity);
}
