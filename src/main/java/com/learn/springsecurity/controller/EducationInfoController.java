package com.learn.springsecurity.controller;

import com.learn.springsecurity.model.EducationInfo;
import com.learn.springsecurity.service.impl.EducationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static com.learn.springsecurity.utils.MyConstant.AUTH;

@RestController
@RequestMapping(AUTH)
public class EducationInfoController {
    @Autowired
    private EducationInfoService educationInfoService;

    @GetMapping("/education-info/view")
    public ResponseEntity<List<EducationInfo>> getAllEducationInfo() {
        return ResponseEntity.ok(educationInfoService.getAllEducationInfo());
    }
    @GetMapping("/education-info/view/{trackNo}")
    public ResponseEntity<EducationInfo> getEducationInfoByTrackNo(@PathVariable String trackNo) {
        Optional<EducationInfo> educationInfo = educationInfoService.getEducationInfoByTrackNo(trackNo);
        return educationInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/education-info/post")
    public ResponseEntity<EducationInfo> createEducationInfo(@RequestBody EducationInfo educationInfo) {
        return ResponseEntity.ok(educationInfoService.saveEducationInfo(educationInfo));
    }

    @PutMapping("/education-info/update/{id}")
    public ResponseEntity<EducationInfo> updateEducationInfo(@PathVariable Long id, @RequestBody EducationInfo educationInfo) {
        return ResponseEntity.ok(educationInfoService.updateEducationInfo(id, educationInfo));
    }

    @DeleteMapping("/education-info/delete/{id}")
    public ResponseEntity<Void> deleteEducationInfo(@PathVariable Long id) {
        educationInfoService.deleteEducationInfo(id);
        return ResponseEntity.noContent().build();
    }
}
