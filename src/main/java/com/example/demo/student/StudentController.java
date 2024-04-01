package com.example.demo.student;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public List<StudentResponseDto> getListStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping("/student/{student-id}")
    public StudentResponseDto getStudentById(
            @PathVariable("student-id") Integer studentId
    ) {
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> getAllStudentByName(
            @PathVariable("student-name") String studentName
    ) {
        return studentService.findAllStudentByName(studentName);
    }

    @PostMapping("/student")
    public StudentResponseDto saveStudent(@RequestBody StudentDto student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> deleteStudent(
            @PathVariable("student-id") Integer studentId
    ) {
        return  studentService.deleteStudent(studentId);
    }

}
