package com.tp.server_back.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/server")
public class ServerController {


    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello";
    }
}
