package com.example.demo.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty
        String studentName,
        int studentAge,
        String email,
        Integer schoolId

) {
}
