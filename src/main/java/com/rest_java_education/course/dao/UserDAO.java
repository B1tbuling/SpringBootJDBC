package com.rest_java_education.course.dao;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;


//createUserTable()
//dropUserTable()
//saveUser()
//removeUserById()
//getAllUsers()
//cleanUserTable()

public interface UserDAO<T> {

    void createUserTable();

    void dropUserTable();

    void cleanUserTable();

//    Optional<T> get(long id);
//
//    List<T> getAll();
//
//    void save(T t);
//
//    void update(T t, String[] params);
//
//    void delete(T t);
}