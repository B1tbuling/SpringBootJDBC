package com.rest_java_education.course.model;


public enum TableNameEnum {
    USER("user"),
    CLIENT("client"),
    ADMIN("admin"),
    MODERATOR("moderator"),
    WORKER("worker");

    private final String tableName;

    TableNameEnum(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}