package com.rest_java_education.course.dao;

import com.rest_java_education.course.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseCrudDaoJdbc implements BaseCrudDAO {
    @Override
    public void dropTable(String tableName) {
        String sql = """
            DROP TABLE "%s";
        """.formatted(tableName);

        try (
            Connection connection = JdbcUtil.open();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица " + tableName + " успешно удалена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void cleanTable(String tableName) {
        String sql = """
            DELETE FROM "%s";
        """.formatted(tableName);

        try (
            Connection connection = JdbcUtil.open();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица " + tableName + " успешно очищена от данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeById(Long id, String tableName) {
        String sql = """
            DELETE FROM "%s" WHERE id = ?;
        """.formatted(tableName);

        try (
            Connection connection = JdbcUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);

            var statementDeleteUser = statement.executeUpdate();

            if (statementDeleteUser > 0) {
                System.out.println("Пользователь успешно удален");
            } else {
                System.out.println("Пользователь не был удален");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}