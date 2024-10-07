package com.learn.springsecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "education_info")
public class EducationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String fieldOfStudy;
    private String graduationYear;
    private String institutionName;
    private String location;
    private String cgpaOrPercentage;
    private String certificationTitle;
    private String certificationInstitution;
    private String certificationYear;

    private String trackNo; // Changed to String

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCgpaOrPercentage() {
        return cgpaOrPercentage;
    }

    public void setCgpaOrPercentage(String cgpaOrPercentage) {
        this.cgpaOrPercentage = cgpaOrPercentage;
    }

    public String getCertificationTitle() {
        return certificationTitle;
    }

    public void setCertificationTitle(String certificationTitle) {
        this.certificationTitle = certificationTitle;
    }

    public String getCertificationInstitution() {
        return certificationInstitution;
    }

    public void setCertificationInstitution(String certificationInstitution) {
        this.certificationInstitution = certificationInstitution;
    }

    public String getCertificationYear() {
        return certificationYear;
    }

    public void setCertificationYear(String certificationYear) {
        this.certificationYear = certificationYear;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }
}
