package com.example.spring_jdbc.repository;

import com.example.spring_jdbc.entity.Student;

import java.util.List;

public interface StudentRepository {
    void createStudent(Student student);

    void updateStudent(String id, Student student);

    void deleteStudent(String id);

    Student getStudentById(String id);

    List<Student> getAllStudents();
}
