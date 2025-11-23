package com.example.jobservice.cv.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cv")
public class Cv {

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String headline;

    public Cv() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getHeadline() { return headline; }
    public void setHeadline(String headline) { this.headline = headline; }
}
