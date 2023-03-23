package com.igfasouza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igfasouza.components.FirmataComponent;

@RestController
public class ArduinoController {

    @Autowired
    private FirmataComponent firmata;

    @GetMapping("/led")
    public ResponseEntity<?> serial(){
        firmata.led();
        return ResponseEntity.ok().build();
    }

}