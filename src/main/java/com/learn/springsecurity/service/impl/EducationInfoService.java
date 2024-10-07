package com.learn.springsecurity.service.impl;

import com.learn.springsecurity.model.EducationInfo;
import com.learn.springsecurity.repository.EducationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationInfoService {
    @Autowired
    private EducationInfoRepository educationInfoRepository;

    public List<EducationInfo> getAllEducationInfo() {
        return educationInfoRepository.findAll();
    }

    public EducationInfo saveEducationInfo(EducationInfo educationInfo) {
        return educationInfoRepository.save(educationInfo);
    }

    public EducationInfo updateEducationInfo(Long id, EducationInfo educationInfo) {
        educationInfo.setId(id);
        return educationInfoRepository.save(educationInfo);
    }
    public Optional<EducationInfo> getEducationInfoByTrackNo(String trackNo) {
        Optional<EducationInfo> getBYTn=educationInfoRepository.findByTrackNo(trackNo);
        return getBYTn;
    }
    public void deleteEducationInfo(Long id) {
        educationInfoRepository.deleteById(id);
    }
}
