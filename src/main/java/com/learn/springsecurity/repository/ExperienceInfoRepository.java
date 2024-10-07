package com.learn.springsecurity.repository;

import com.learn.springsecurity.model.ExperienceInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceInfoRepository extends JpaRepository<ExperienceInfo, Long> {
        Optional<ExperienceInfo> findByTrackNo(String trackNo);

}
