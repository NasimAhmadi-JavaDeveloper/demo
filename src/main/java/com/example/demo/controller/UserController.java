package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/{id}/permissions")
    public ResponseEntity<UserDto> grantPermission(@PathVariable Long id, @RequestParam Long serviceId) {
        return ResponseEntity.ok(userService.grantServicePermission(id, serviceId));
    }
}
