package com.example.spring_jdbc.mapper;

import com.example.spring_jdbc.entity.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));

        return student;
    }
}
