package com.example.demo;


import jakarta.persistence.*;

@Entity
@Table(name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column(name = "name")
    private String studentName;
    @Column(name = "age")
    private int studentAge;

    @Column(name = "email", unique = true)
    private String email;


    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    public Student() {
    }

    public Student(String studentName, int studentAge, String email) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.email = email;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public String getEmail() {
        return email;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
