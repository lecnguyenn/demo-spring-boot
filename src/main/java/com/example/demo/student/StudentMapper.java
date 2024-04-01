package com.example.demo.student;

import com.example.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto) {
        var student = new Student();
        student.setStudentName(dto.studentName());
        student.setStudentAge(dto.studentAge());
        student.setEmail(dto.email());
        var school = new School();
        school.setSchoolId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getStudentName(),
                student.getStudentAge(),
                student.getEmail()
        );
    }
}
