package com.example.demo.school;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto createSchool(
            @RequestBody SchoolDto schoolDto
    ){
        var school = schoolMapper.toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    public List<SchoolDto> getListSchool(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());

    }
}

