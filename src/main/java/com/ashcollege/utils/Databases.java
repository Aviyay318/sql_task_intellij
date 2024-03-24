package com.ashcollege.utils;

import com.ashcollege.entities.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

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

    public boolean usernameAvailable (String username) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT users.username " +
                                    "FROM users WHERE username = ? ");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
