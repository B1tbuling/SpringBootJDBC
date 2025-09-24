package com.rest_java_education.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.rest_java_education.course.model.TableNameEnum;
import com.rest_java_education.course.model.User;
import com.rest_java_education.course.utils.JdbcUtil;
import com.rest_java_education.course.utils.SqlQueryBuilder;


public class UserDaoJdbcImp implements UserDAO {

    public void createUserTable() {
        String sql = """
            CREATE TABLE "user" (
                id INTEGER PRIMARY KEY NOT NULL,
                name VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                phone_number VARCHAR NOT NULL
             );
        """;

        try (
            Connection connection = JdbcUtil.open();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица user успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUserTable() {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        String sql = sqlQueryBuilder.generateDropTableQuery("users");

        try (
            Connection connection = JdbcUtil.open();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица user успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cleanUserTable() {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        String sql = sqlQueryBuilder.generateCleanTableQuery("users");

        try (
            Connection connection = JdbcUtil.open();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица user успешно очищена от данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(User user) {
        String sql = """
            INSERT INTO "user" ( id, name, email, phone_number ) VALUES ( ?, ?, ?, ? );
        """;

        try (
            Connection connection = JdbcUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());

            statement.executeUpdate();

            System.out.println("Пользователь успено создан");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(Long id) {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        String sql = sqlQueryBuilder.generateRemoveByIdQuery(id, "users");

        try (
            Connection connection = JdbcUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
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


    @Override
    public void removeUser(User user) {}


    @Override
    public List<User> getAllUsers() {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        String sql = sqlQueryBuilder.generateFindAllQuery("users");

        List<User> users = new ArrayList<>();

        try (
            Connection connection = JdbcUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            var result = statement.executeQuery();

            while (result.next()) {
                users.add(
                    new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("phone_number")
                    )
                );
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}