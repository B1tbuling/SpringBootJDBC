package com.rest_java_education.course;

import com.rest_java_education.course.service.UserService;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();

        userService.createUserTable();

        userService.dropUserTable();

    }
}