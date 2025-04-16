package com.example.spring_jdbc.repository;

import com.example.spring_jdbc.entity.Student;
import com.example.spring_jdbc.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcStudentRepository implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentMapper mapper;

    @Override
    public void createStudent(Student student) {
        String sql = "INSERT INTO students (id, name, email) VALUES (?, ?, ?)";
        Object[] params = {student.getId(), student.getName(), student.getEmail()};
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateStudent(String id, Student student) {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        Object[] params = { student.getName(), student.getEmail(), id };
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE id = ?";
        Object[] params = { id };
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Student getStudentById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, params, mapper);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, mapper);
    }
}
