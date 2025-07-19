package com.projectsky.bishopprototype.controller;

import com.projectsky.bishopprototype.service.AndroidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/android")
@RequiredArgsConstructor
public class AndroidController {

    private final AndroidService androidService;

    @GetMapping
    public ResponseEntity<String> getStatus(){
        return ResponseEntity.ok(androidService.checkAndroidStatus());
    }
}
