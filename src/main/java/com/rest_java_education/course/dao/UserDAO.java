package com.rest_java_education.course.dao;

import java.util.List;

import com.rest_java_education.course.model.User;


public interface UserDAO {

    void createUserTable();

    void dropUserTable();

    void cleanUserTable();

    void saveUser(User user);

    void getAllUsers();

    void removeUserById(Long id);
}