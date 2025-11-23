package com.example.jobservice.service;

import com.example.jobservice.cv.model.Cv;
import com.example.jobservice.cv.repository.CvRepository;
import com.example.jobservice.job.model.Job;
import com.example.jobservice.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobRecommendationService {

    private final CvRepository cvRepository;
    private final JobRepository jobRepository;

    public JobRecommendationService(CvRepository cvRepository, JobRepository jobRepository) {
        this.cvRepository = cvRepository;
        this.jobRepository = jobRepository;
    }

    public List<Job> getRecommendedJobsByUserId(Long userId) {
        System.out.println("[DEBUG] Looking for CV of userId: " + userId);

        Optional<Cv> cvOptional = cvRepository.findByUserId(userId);
        if (cvOptional.isEmpty()) {
            System.out.println("[DEBUG] No CV found for userId: " + userId);
            return List.of(); // empty list
        }

        Cv cv = cvOptional.get();
        System.out.println("[DEBUG] CV found: " + cv.getHeadline());

        List<Job> jobs = jobRepository.findByTitleContainingIgnoreCase(cv.getHeadline());
        System.out.println("[DEBUG] Number of recommended jobs found: " + jobs.size());

        return jobs;
    }
}
