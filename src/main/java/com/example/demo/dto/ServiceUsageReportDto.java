package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceUsageReportDto {
    private Long userId;
    private String username;
    private Long serviceId;
    private String serviceName;
    private Integer usageCount;
}
