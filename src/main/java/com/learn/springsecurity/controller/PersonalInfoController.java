package com.learn.springsecurity.controller;

import com.learn.springsecurity.model.PersonalInfo;
import com.learn.springsecurity.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static com.learn.springsecurity.utils.MyConstant.AUTH;

@RestController
@RequestMapping(AUTH)
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    // Get all personal info
    @GetMapping("/personal-info/view")
    public ResponseEntity<List<PersonalInfo>> getAllPersonalInfo() {
        return ResponseEntity.ok(personalInfoService.getAllPersonalInfo());
    }
    
    // Create new personal info
    @PostMapping("/personal-info/post")
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        return ResponseEntity.ok(personalInfoService.savePersonalInfo(personalInfo));
    }

    // Update personal info by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
        Optional<PersonalInfo> existingPersonalInfo = personalInfoService.getPersonalInfoById(id);
        if (existingPersonalInfo.isPresent()) {
            personalInfo.setId(id); // Ensure we update the correct entry
            return ResponseEntity.ok(personalInfoService.savePersonalInfo(personalInfo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete personal info by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable Long id) {
        personalInfoService.deletePersonalInfo(id);
        return ResponseEntity.noContent().build();
    }
    // Get personal info by trackNo
    @GetMapping("/personal-info/view/{trackNo}")
    public ResponseEntity<PersonalInfo> getPersonalInfoByTrackNo(@PathVariable String trackNo) {
        Optional<PersonalInfo> personalInfo = personalInfoService.getPersonalInfoByTrackNo(trackNo);
        return personalInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
