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

    /**
     * Inserts a new student record into the database.
     *
     * @param student the Student object containing the details to be inserted.
     */
    @Override
    public void createStudent(Student student) {
        String sql = "INSERT INTO students (id, name, email) VALUES (?, ?, ?)";
        Object[] params = {student.getId(), student.getName(), student.getEmail()};
        jdbcTemplate.update(sql, params);
    }

    /**
     * Updates an existing student record in the database.
     *
     * @param id the unique identifier of the student to be updated (String).
     * @param student the Student object containing the updated details.
     */
    @Override
    public void updateStudent(String id, Student student) {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        Object[] params = { student.getName(), student.getEmail(), id };
        jdbcTemplate.update(sql, params);
    }

    /**
     * Deletes a student record from the database.
     *
     * @param id the unique identifier of the student to be deleted (String).
     */
    @Override
    public void deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE id = ?";
        Object[] params = { id };
        jdbcTemplate.update(sql, params);
    }

    /**
     * Retrieves a student record from the database by its unique identifier.
     *
     * @param id the unique identifier of the student to be retrieved (String).
     * @return the Student object corresponding to the given id.
     */
    @Override
    public Student getStudentById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, params, mapper);
    }

    /**
     * Retrieves all student records from the database.
     *
     * @return a list of Student objects representing all students in the database.
     *
     * Uses a RowMapper to map each row of the result set to a Student object.
     */
    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, mapper);
    }
}
