package com.learn.springsecurity.repository;

import com.learn.springsecurity.model.EducationInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {
        Optional<EducationInfo> findByTrackNo(String trackNo);

}
