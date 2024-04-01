package com.example.demo.school;


import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class School {


    @Id
    @GeneratedValue
    private Integer schoolId;
    private String schoolName;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
