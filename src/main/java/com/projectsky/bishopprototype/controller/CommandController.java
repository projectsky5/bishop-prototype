package com.projectsky.bishopprototype.controller;

import com.projectsky.synthetichumancorestarter.command.model.Command;
import com.projectsky.synthetichumancorestarter.command.service.CommandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommandController {

    private final CommandService commandService;

    @PostMapping
    public ResponseEntity<Void> createCommand(
            @Valid @RequestBody Command command
    ){
        commandService.process(command);
        return ResponseEntity.ok().build();
    }
}
