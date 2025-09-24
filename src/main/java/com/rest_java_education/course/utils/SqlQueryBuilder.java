package com.rest_java_education.course.utils;

import com.rest_java_education.course.model.TableNameEnum;


public class SqlQueryBuilder {
    public String generateDropTableQuery(String tableName) {
        return """
           DROP TABLE "%s";
        """.formatted(tableName);
    }

    public String generateCleanTableQuery(String tableName) {
        return """
            DELETE FROM "%s";
        """.formatted(tableName);
    }

    public String generateRemoveByIdQuery(Long id, String tableName) {
        return """
            DELETE FROM "%s" WHERE id = %d;
        """.formatted(tableName, id);
    }

    public String generateFindAllQuery(String tableName) {
        return """
            SELECT * FROM "%s";
        """.formatted(tableName);
    }
}
