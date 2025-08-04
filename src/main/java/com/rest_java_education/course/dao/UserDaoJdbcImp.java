package com.rest_java_education.course.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.rest_java_education.course.model.User;
import com.rest_java_education.course.utils.JdbcUtil;


public class UserDaoJdbcImp implements UserDAO {

    String createUserTableSQL = """
        CREATE TABLE "user" (
            id INTEGER PRIMARY KEY NOT NULL,
            name VARCHAR NOT NULL,
            email VARCHAR NOT NULL,
            phone_number VARCHAR NOT NULL
         );
    """;

    String dropUserTableSQL = """
        DROP TABLE "user";
    """;

    String cleanUserTableSQL = """
        DELETE FROM "user";
    """;

    String crateUserSQL = """
        INSERT INTO "user" ( id, name, email, phone_number ) VALUES ( ?, ?, ?, ? );
    """;

    String getAllUsersSQL = """
        SELECT * FROM "user";
    """;

    String deleteUserByIdSQL = """
        DELETE FROM "user" WHERE id = ?;
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


    @Override
    public void saveUser(User user) {
        try (
                var connection = JdbcUtil.open();
                var statement = connection.prepareStatement(crateUserSQL);
        ) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());

            statement.executeUpdate();

            System.out.println("Пользователь успено создан");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void removeUserById(Long id) {
        try (
            var connection = JdbcUtil.open();
            var statement = connection.prepareStatement(deleteUserByIdSQL);
        ) {
            statement.setLong(1, id);

            var statementDeleteUser = statement.executeUpdate();

            if (statementDeleteUser > 0) {
                System.out.println("Пользователь успешно удален");
            } else {
                System.out.println("Пользователь не был удален");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void getAllUsers() {
        try (
            var connection = JdbcUtil.open();
            var statement = connection.prepareStatement(getAllUsersSQL);
        ) {
            List<User> users = new ArrayList<>();

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

            System.out.println("Все пользователи: " + users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}