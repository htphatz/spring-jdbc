package com.example.spring_jdbc.controller;

import com.example.spring_jdbc.entity.Student;
import com.example.spring_jdbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            studentRepository.createStudent(student);
            return ResponseEntity.ok("Create");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.ok("Student with id " + student.getId() + " already existed");
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") String id) {
        try {
            Student student = studentRepository.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.ok("Student with id " + id + " not found");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        try {
            studentRepository.updateStudent(id, student);
            return ResponseEntity.ok("Update successful");
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.ok("Student with id " + id + " not found");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) {
        studentRepository.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
