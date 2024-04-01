package com.example.demo.student;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDto> findAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }
    public StudentResponseDto findStudentById(Integer studentId) {
        boolean existStudents = studentRepository.existsById(studentId);
        if(!existStudents) {
             throw new IllegalStateException("Not found student with ID: " + studentId);
        }
        return studentRepository.findById(studentId).map(studentMapper::toStudentResponseDto).orElse(null);
    }

    public List<StudentResponseDto> findAllStudentByName(String studentName) {
        return studentRepository.findAllByStudentNameContaining(studentName)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDto> deleteStudent(Integer studentId) {
        boolean existStudents = studentRepository.existsById(studentId);
        if(!existStudents) {
            throw new IllegalStateException("student with ID: " + studentId + "dost not exits");
        }
        studentRepository.deleteById(studentId);
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());

    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }
}
