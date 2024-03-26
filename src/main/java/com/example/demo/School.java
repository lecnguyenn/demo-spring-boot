package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class School {


    @Id
    @GeneratedValue
    private Integer schoolId;
    private String schoolName;

    public Integer getSchoolId() {
        return schoolId;
    }

    public School() {
    }

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
