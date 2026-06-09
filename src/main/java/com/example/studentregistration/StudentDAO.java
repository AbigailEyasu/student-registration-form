package com.example.studentregistration;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * StudentDAO (Data Access Object) handles all database operations for Students.
 * Implements CRUD (Create, Read, Update, Delete) operations.
 */
public class StudentDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Add a new student to the database
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (full_name, student_id, year, age, gender, email, phone_number, emergency_contact) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getStudentId());
            pstmt.setString(3, student.getYear());
            pstmt.setInt(4, student.getAge());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getEmail());
            pstmt.setString(7, student.getPhoneNumber());
            pstmt.setString(8, student.getEmergencyContact());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Student added: " + student.getFullName());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding student", e);
        }
        return false;
    }

    /**
     * Get all students from database
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY registered_date DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = extractStudentFromResultSet(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all students", e);
        }
        return students;
    }

    /**
     * Get student by Student ID (e.g., NaScR/1111/25)
     */
    public Student getStudentByStudentId(String studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving student by student ID", e);
        }
        return null;
    }

    /**
     * Update student information
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET full_name=?, year=?, age=?, gender=?, email=?, phone_number=?, emergency_contact=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getYear());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPhoneNumber());
            pstmt.setString(7, student.getEmergencyContact());
            pstmt.setInt(8, student.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("Student updated: " + student.getFullName());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student", e);
        }
        return false;
    }

    /**
     * Delete student by ID
     */
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                LOGGER.info("Student deleted with ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student", e);
        }
        return false;
    }

    /**
     * Get total number of students
     */
    public int getTotalStudentCount() {
        String sql = "SELECT COUNT(*) FROM students";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting student count", e);
        }
        return 0;
    }

    /**
     * Helper method to extract Student object from ResultSet
     */
    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String fullName = rs.getString("full_name");
        String studentId = rs.getString("student_id");
        String year = rs.getString("year");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phone_number");
        String emergencyContact = rs.getString("emergency_contact");
        LocalDateTime registeredDate = rs.getTimestamp("registered_date").toLocalDateTime();

        return new Student(id, fullName, studentId, year, age, gender, email, phoneNumber, emergencyContact, registeredDate);
    }
}
