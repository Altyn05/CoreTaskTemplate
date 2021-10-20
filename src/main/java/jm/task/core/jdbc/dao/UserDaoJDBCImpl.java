package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static java.lang.String.valueOf;

public class UserDaoJDBCImpl implements UserDao {

    Savepoint savepoint;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (45), lastname VARCHAR (45), age INT )");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }

        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.prepareStatement("INSERT INTO users (id, name, lastName, age) VALUES (DEFAULT,  ?, ?, ?);");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, valueOf(age));
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        ResultSet res = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setAge(res.getByte("age"));
                list.add(user);
            }
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException resex) {
                    resex.printStackTrace();
                }
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("SavepointOne");
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException rollex) {
                rollex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
