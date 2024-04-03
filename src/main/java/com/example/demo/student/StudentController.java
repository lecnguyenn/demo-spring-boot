package com.example.demo.student;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
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
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> deleteStudent(
            @PathVariable("student-id") Integer studentId
    ) {
        return  studentService.deleteStudent(studentId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errMessage = error.getDefaultMessage();
            errors.put(fieldName, errMessage);
        });
        System.out.println("error" + errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
