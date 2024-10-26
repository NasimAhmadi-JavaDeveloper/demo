package com.example.demo.sevice;

import com.example.demo.dto.ServiceUsageReportDto;
import com.example.demo.mapper.UserServiceUsageMapper;
import com.example.demo.repository.UserServiceUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportingService {

    private final UserServiceUsageRepository userServiceUsageRepository;
    private final UserServiceUsageMapper userServiceUsageMapper;

    public List<ServiceUsageReportDto> getServiceUsageByUserId(Long userId) {
        return userServiceUsageRepository.findAllByUserId(userId)
                .stream()
                .map(userServiceUsageMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ServiceUsageReportDto> getServiceUsageByServiceId(Long serviceId) {
        return userServiceUsageRepository.findAllByServiceId(serviceId)
                .stream()
                .map(userServiceUsageMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ServiceUsageReportDto> getAllServiceUsages() {
        return userServiceUsageRepository.findAll()
                .stream()
                .map(userServiceUsageMapper::toDto)
                .collect(Collectors.toList());
    }

}
