package com.learn.springsecurity.service;

import com.learn.springsecurity.model.PersonalInfo;
import com.learn.springsecurity.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoService {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    // Get all personal info records
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoRepository.findAll();
    }

    // Save personal info
    public PersonalInfo savePersonalInfo(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    // Get personal info by ID
    public Optional<PersonalInfo> getPersonalInfoById(Long id) {
        return personalInfoRepository.findById(id);
    }
    // Get personal info by trackNo
    public Optional<PersonalInfo> getPersonalInfoByTrackNo(String trackNo) {
        Optional<PersonalInfo> getBYTn=personalInfoRepository.findByTrackNo(trackNo);
        return getBYTn;
    }
    // Delete personal info by ID
    public void deletePersonalInfo(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
