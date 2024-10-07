package com.learn.springsecurity.controller;

import com.learn.springsecurity.model.ExperienceInfo;
import com.learn.springsecurity.service.impl.ExperienceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.learn.springsecurity.utils.MyConstant.AUTH;

@RestController
@RequestMapping(AUTH)
public class ExperienceInfoController {
    @Autowired
    private ExperienceInfoService experienceInfoService;

    @GetMapping("/experience-info/view")
    public ResponseEntity<List<ExperienceInfo>> getAllExperienceInfo() {
        return ResponseEntity.ok(experienceInfoService.getAllExperienceInfo());
    }
    @GetMapping("/experience-info/view/{trackNo}")
    public ResponseEntity<ExperienceInfo> getPersonalInfoByTrackNo(@PathVariable String trackNo) {
        Optional<ExperienceInfo> experienceInfo = experienceInfoService.getExperienceInfoByTrackNo(trackNo);
        return experienceInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/experience-info/post")
    public ResponseEntity<ExperienceInfo> createExperienceInfo(@RequestBody ExperienceInfo experienceInfo) {
        return ResponseEntity.ok(experienceInfoService.saveExperienceInfo(experienceInfo));
    }

    @PutMapping("/experience-info/update/{id}")
    public ResponseEntity<ExperienceInfo> updateExperienceInfo(@PathVariable Long id, @RequestBody ExperienceInfo experienceInfo) {
        return ResponseEntity.ok(experienceInfoService.updateExperienceInfo(id, experienceInfo));
    }

    @DeleteMapping("/experience-info/delete/{id}")
    public ResponseEntity<Void> deleteExperienceInfo(@PathVariable Long id) {
        experienceInfoService.deleteExperienceInfo(id);
        return ResponseEntity.noContent().build();
    }
}
