package com.example.jobservice.cv.repository;

import com.example.jobservice.cv.model.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CvRepository extends JpaRepository<Cv, Long> {
    Optional<Cv> findByUserId(Long userId);
}
