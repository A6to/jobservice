package com.example.jobservice.cv.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cv-service", url = "${services.cv.url}")
public interface CvClient {

    @GetMapping("/cv/{id}")
    CvResponse getCvById(@PathVariable("id") Long id);
}

