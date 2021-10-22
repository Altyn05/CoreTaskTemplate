package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 20);

        userService.saveUser("Петр", "Петров", (byte) 21);
        userService.saveUser("Jon", "Jonson", (byte) 22);
        userService.saveUser("Азиз", "Азизов", (byte) 23);
        userService.removeUserById(3L);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
