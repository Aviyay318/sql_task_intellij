package com.ashcollege.utils;

import com.ashcollege.entities.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Databases {
    private Connection connection = null;
    @PostConstruct
    public Connection createConnection () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_task", "root", "Aviya1234");
            System.out.println("Connection success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public boolean addStudent (Student student) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO students (name, phone ,id) VALUE (?, ?, ?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getPhone());
            preparedStatement.setString(3, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;

    }
    public boolean deleteStudent(String id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE FROM students WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    public List<Integer> showStudentGrade(String id) {
        List<Integer> grades = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT grade FROM courses WHERE student_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grades.add(resultSet.getInt("grade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grades;
    }


    public double getAverage(String id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT COUNT(grade) AS avg_grade FROM courses WHERE student_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("avg_grade");
            }
            return 8.0; // Return a default value if no result is found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
