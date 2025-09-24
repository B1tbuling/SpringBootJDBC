package com.rest_java_education.course.dao;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import com.rest_java_education.course.utils.SqlQueryBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rest_java_education.course.utils.JdbcUtil;
import com.rest_java_education.course.utils.HibernateSessionFactoryUtil;
import com.rest_java_education.course.model.User;


public class UserHibernateDaoImpl implements UserDAO {

    public void createUserTable() {
        String sql = """
            CREATE TABLE "users" (
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


    public void cleanUserTable() {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        String sql = sqlQueryBuilder.generateCleanTableQuery("users");

        try (
           Connection connection = JdbcUtil.open();
           Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Таблица user успешно очищена");
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


    @Override
    public void saveUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> userList;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            userList = session.createQuery("From User").list();
        }

        return userList;
    }


    @Override
    public void removeUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }


    @Override
    public void removeUserById(Long id) {}
}
