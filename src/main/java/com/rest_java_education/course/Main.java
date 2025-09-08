package com.rest_java_education.course;

import java.util.List;

import com.rest_java_education.course.model.User;
import com.rest_java_education.course.service.BaseCrudService;
import com.rest_java_education.course.service.UserService;


public class Main {
    public static void main(String[] args) {
//        enum Entity {
//            USER("user"),
//            CLIENT("client"),
//            ADMIN("client"),
//            MODERATOR("client"),
//            WORKER("worker"),
//        }


        BaseCrudService baseCrudService = new BaseCrudService();
        UserService userService = new UserService();

        userService.createUserTable();

        User user1 = new User(1L, "Misha", "misha@email.com", "8 999 999 99 91");
        User user2 = new User(2L, "Alex", "alex@email.com", "8 999 999 99 92");
        User user3 = new User(3L, "Georg", "georg@email.com", "8 999 999 99 93");
        User user4 = new User(4L, "Debil", "debil@email.com", "8 999 999 99 94");

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);

        List<User> users = userService.getAllUsers();
        System.out.println("Все пользователи: " + users);

        baseCrudService.removeById(1L, "user");
        //userService.removeUserById(1L);

        baseCrudService.cleanTable("user");
        //userService.cleanUserTable();

        baseCrudService.dropTable("user");
        //userService.dropUserTable();
    }
}