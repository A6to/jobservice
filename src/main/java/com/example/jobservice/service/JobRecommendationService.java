package com.example.jobservice.service;

import com.example.jobservice.cv.client.CvClient;
import com.example.jobservice.cv.client.CvResponse;
import com.example.jobservice.job.model.Job;
import com.example.jobservice.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JobRecommendationService {

    private final CvClient cvClient;
    private final JobRepository jobRepository;

    public JobRecommendationService(CvClient cvClient, JobRepository jobRepository) {
        this.cvClient = cvClient;
        this.jobRepository = jobRepository;
    }

    public List<Job> getRecommendedJobsByUserId(Long userId) {
        System.out.println("[DEBUG] Calling NestJS CV service for userId: " + userId);

        CvResponse cv = cvClient.getCvById(userId); // Feign call

        if (cv == null) {
            System.out.println("[DEBUG] No CV found in NestJS for userId: " + userId);
            return Collections.emptyList();
        }

        System.out.println("[DEBUG] CV found: " + cv.getHeadline());

        List<Job> jobs =
                jobRepository.findByTitleContainingIgnoreCase(cv.getHeadline());

        System.out.println("[DEBUG] Recommended jobs found: " + jobs.size());

        return jobs;
    }
}
