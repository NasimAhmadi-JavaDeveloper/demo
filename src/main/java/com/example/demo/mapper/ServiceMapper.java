package com.example.demo.mapper;

import com.example.demo.dto.ServiceDto;
import com.example.demo.entity.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
   ServiceDto mapToDto(ServiceEntity entity);
   List<ServiceDto> mapToDtoList(List<ServiceEntity> entity);

   ServiceEntity mapToEntity(ServiceDto dto);
}
