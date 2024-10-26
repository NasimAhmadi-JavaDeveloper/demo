package com.example.demo.controller;

import com.example.demo.dto.ServiceUsageReportDto;
import com.example.demo.sevice.ReportingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportingController {

    private final ReportingService reportingService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ServiceUsageReportDto>> getServiceUsageByUserId(@PathVariable Long userId) {
        List<ServiceUsageReportDto> report = reportingService.getServiceUsageByUserId(userId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<List<ServiceUsageReportDto>> getServiceUsageByServiceId(@PathVariable Long serviceId) {
        List<ServiceUsageReportDto> report = reportingService.getServiceUsageByServiceId(serviceId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceUsageReportDto>> getAllServiceUsages() {
        List<ServiceUsageReportDto> report = reportingService.getAllServiceUsages();
        return ResponseEntity.ok(report);
    }
}
