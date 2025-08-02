package com.rest_java_education.course.dao;

import java.sql.SQLException;

import com.rest_java_education.course.model.User;
import com.rest_java_education.course.utils.JdbcUtil;



public class UserDaoJdbcImp implements UserDAO<User> {

    String createUserTableSQL = """
        CREATE TABLE "user" ( 
            id INTEGER PRIMARY KEY NOT NULL,
            name CHAR(50) NOT NULL,
            email CHAR(50) NOT NULL,
            phone_number CHAR(12) NOT NULL
         );
    """;

    String dropUserTableSQL = """
        DROP TABLE "user";
    """;

    String cleanUserTableSQL = """
        DELETE FROM "user";
    """;


    @Override
    public void createUserTable() {
        try (
            var connection = JdbcUtil.open();
            var statement = connection.createStatement();
        ) {
            statement.execute(createUserTableSQL);
            System.out.println("Таблица user успешно создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void dropUserTable() {
        try (
            var connection = JdbcUtil.open();
            var statement = connection.createStatement();
        ) {
            statement.execute(dropUserTableSQL);
            System.out.println("Таблица user успешно удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void cleanUserTable() {
        try (
                var connection = JdbcUtil.open();
                var statement = connection.createStatement();
        ) {
            statement.execute(cleanUserTableSQL);
            System.out.println("Таблица user успешно очищена от данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}