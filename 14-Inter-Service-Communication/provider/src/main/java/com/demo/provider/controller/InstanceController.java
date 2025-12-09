package com.demo.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstanceController {

    @Value(("${server.port}"))
    private String port;

    private final String instanceID = java.util.UUID.randomUUID().toString();

    @GetMapping("/instance-info")
    public String getInstanceInfo() {
        System.out.println("Request Received at Instance running on port : " + port);

        return "Instance served by port : " + port + ". Instance ID: " + instanceID;
    }
}
