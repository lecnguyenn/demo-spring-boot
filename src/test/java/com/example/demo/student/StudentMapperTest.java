package com.example.demo.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com",
                1);
        Student student = mapper.toStudent(dto);
        Assertions.assertEquals(dto.studentName(),student.getStudentName());
        Assertions.assertEquals(dto.studentAge(), student.getStudentAge());
        Assertions.assertEquals(dto.email(), student.getEmail());
        Assertions.assertNotNull(student.getSchool());
        Assertions.assertEquals(dto.schoolId(), student.getSchool().getSchoolId());

    }
}