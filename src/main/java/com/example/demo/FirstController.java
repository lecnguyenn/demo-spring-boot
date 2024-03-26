package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController {

    private final StudentRepository studentRepository;

    public FirstController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getListStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{student-id}")
    public Student getStudentById(
            @PathVariable("student-id") Integer studentId
    ) {
        return studentRepository.findById(studentId).orElse(new Student());
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> getAllStudentByName(
            @PathVariable("student-name") String studentName
    ) {
        return studentRepository.findAllByStudentNameContaining(studentName);
    }

    @PostMapping("/student")
    public Student postStudent(@RequestBody Student student) {
        return  studentRepository.save(student);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> deleteStudent(
            @PathVariable("student-id") Integer studentId
    ) {
        studentRepository.deleteById(studentId);
        return studentRepository.findAll();
    }

}
