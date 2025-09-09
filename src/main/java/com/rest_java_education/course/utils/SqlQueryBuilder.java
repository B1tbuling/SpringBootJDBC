package com.rest_java_education.course.utils;

import com.rest_java_education.course.model.TableNameEnum;



public class SqlQueryBuilder {
    public String generateDropTableQuery(TableNameEnum tableName) {
        return """
           DROP TABLE "%s";
        """.formatted(tableName.getTableName());
    }

    public String generateCleanTableQuery(TableNameEnum tableName) {
        return """
            DELETE FROM "%s";
        """.formatted(tableName.getTableName());
    }

    public String generateRemoveByIdQuery(Long id, TableNameEnum tableName) {
        return """
            DELETE FROM "%s" WHERE id = %d;
        """.formatted(tableName.getTableName(), id);
    }

    public String generateFindAllQuery(TableNameEnum tableName) {
        return """
            SELECT * FROM "%s";
        """.formatted(tableName.getTableName());
    }
}
