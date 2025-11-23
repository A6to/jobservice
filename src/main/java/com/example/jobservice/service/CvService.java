package com.example.jobservice.service;

import com.example.jobservice.cv.client.CvClient;
import com.example.jobservice.cv.client.CvResponse;
import org.springframework.stereotype.Service;

@Service
public class CvService {

    private final CvClient cvClient;

    public CvService(CvClient cvClient) {
        this.cvClient = cvClient;
    }

    public CvResponse getCv(Long id) {
        return cvClient.getCvById(id);
    }
}
