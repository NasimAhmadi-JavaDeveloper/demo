package com.example.demo.controller;

import com.example.demo.dto.ServiceDto;
import com.example.demo.sevice.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final ServiceService service;

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        return ResponseEntity.ok(service.getAllServices());
    }

    @PostMapping
    public ResponseEntity<ServiceDto> create(@RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.ok(service.createService(serviceDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> update(@PathVariable Long id, @RequestBody @Valid ServiceDto serviceDto) {
        return ResponseEntity.ok(service.updateService(id, serviceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceDto>> getActiveServices() {
        return ResponseEntity.ok(service.getActiveServices());
    }
}