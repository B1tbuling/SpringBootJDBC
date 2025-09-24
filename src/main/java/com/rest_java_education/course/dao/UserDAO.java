package com.rest_java_education.course.dao;

import java.util.List;

import com.rest_java_education.course.model.User;


public interface UserDAO {
    void saveUser(User user);

    List<User> getAllUsers();

    void removeUserById(Long id);

    void removeUser(User user);
}