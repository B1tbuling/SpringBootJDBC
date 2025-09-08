package com.rest_java_education.course.dao;


public interface BaseCrudDAO {
    void dropTable(String tableName);

    void cleanTable(String tableName);

    void removeById(Long id, String tableName);
}