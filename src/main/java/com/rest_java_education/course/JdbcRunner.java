package com.rest_java_education.course;

import java.sql.SQLException;
import com.rest_java_education.course.utils.ConnectionManager;


public class JdbcRunner {
    public static void main(String[] args) throws SQLException {

        try (var connection = ConnectionManager.open()) {
           System.out.println(connection.getTransactionIsolation());
        }
    }
}
