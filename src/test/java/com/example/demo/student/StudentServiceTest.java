package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentRepository studentRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        StudentDto dto = new StudentDto(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com",
                1
        );
        Student student = new Student(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com"
        );
        Student savedStudent = new Student(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com"
        );
        savedStudent.setStudentId(1);

        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("Nguyen", 25, "dev.nguyenle@gmail.com"));

        StudentResponseDto responseDto = studentService.saveStudent(dto);
        assertEquals(dto.studentName(), responseDto.studentName());
        assertEquals(dto.studentAge(), responseDto.studentAge());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_student() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com"
        ));
//Mock the call
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(
                        new StudentResponseDto(
                                "Nguyen",
                                25,
                                "dev.nguyenle@gmail.com"
                        )
                );

        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_Id() {
        Integer studentId = 1;
        Student student = new Student(
                "Nguyen",
                25,
                "dev.nguyenle@gmail.com"
        );

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "Nguyen",
                        25,
                        "dev.nguyenle@gmail.com"
                )
        );
        StudentResponseDto responseDto = studentService.findStudentById(studentId);

        assertEquals(responseDto.studentName(), student.getStudentName());
        assertEquals(responseDto.studentAge(), student.getStudentAge());
        assertEquals(responseDto.email(), student.getEmail());

        verify(studentRepository, times(1)).findById(studentId);
    }

}