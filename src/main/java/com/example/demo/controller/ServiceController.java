package com.example.demo.controller;

import com.example.demo.dto.ServiceDto;
import com.example.demo.sevice.ServiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Service")
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final ServiceService service;

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        return ResponseEntity.ok(service.getAllServices());
    }

    @PostMapping
    public ResponseEntity<ServiceDto> create(@RequestBody @Valid ServiceDto dto) {
        return ResponseEntity.ok(service.createService(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> update(@PathVariable Long id, @RequestBody @Valid ServiceDto dto) {
        return ResponseEntity.ok(service.updateService(id, dto));
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

    @PutMapping("/activate/{id}/{userId}")
    public ResponseEntity<Void> activate(@PathVariable Long id, @PathVariable Long userId) {
        service.activateService(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deactivate/{id}/{userId}")
    public ResponseEntity<Void> deActivate(@PathVariable Long id, @PathVariable Long userId) {
        service.deactivateService(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/use-service/{id}/{userId}")
    public ResponseEntity<Void> usage(@PathVariable Long id, @PathVariable Long userId) {
        service.useService(id, userId);
        return ResponseEntity.noContent().build();
    }
}