package com.learn.springsecurity.service.impl;

import com.learn.springsecurity.model.ExperienceInfo;
import com.learn.springsecurity.repository.ExperienceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceInfoService {
    @Autowired
    private ExperienceInfoRepository experienceInfoRepository;

    public List<ExperienceInfo> getAllExperienceInfo() {
        return experienceInfoRepository.findAll();
    }

    public ExperienceInfo saveExperienceInfo(ExperienceInfo experienceInfo) {
        return experienceInfoRepository.save(experienceInfo);
    }

    public ExperienceInfo updateExperienceInfo(Long id, ExperienceInfo experienceInfo) {
        experienceInfo.setId(id);
        return experienceInfoRepository.save(experienceInfo);
    }
    public Optional<ExperienceInfo> getExperienceInfoByTrackNo(String trackNo) {
        Optional<ExperienceInfo> getBYTn=experienceInfoRepository.findByTrackNo(trackNo);
        return getBYTn;
    }
// De
    public void deleteExperienceInfo(Long id) {
        experienceInfoRepository.deleteById(id);
    }
}
