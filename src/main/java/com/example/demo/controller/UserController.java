package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.sevice.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;

@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/{id}/grant_permissions")
    public ResponseEntity<UserDto> grantPermission(@PathVariable Long id, @RequestParam Long serviceId) {
        return ResponseEntity.ok(userService.grantServicePermission(id, serviceId));
    }

    @PostMapping("/{id}/revoke_permissions")
    public ResponseEntity<UserDto> revokePermission(@PathVariable Long id, @RequestParam Long serviceId) {
        return ResponseEntity.ok(userService.revokeServicePermission(id, serviceId));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto.Insert dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/credit")
    public ResponseEntity<UserDto.Credit> allocateCredit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(userService.allocateCredit(id, amount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto.Update dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @GetMapping("/{username}/authorized_services")
    public ResponseEntity<Set<UserDto.Permission>> getAuthorizedServices(@PathVariable String username) {
        return ResponseEntity.ok(userService.getAuthorizedServices(username));
    }
}