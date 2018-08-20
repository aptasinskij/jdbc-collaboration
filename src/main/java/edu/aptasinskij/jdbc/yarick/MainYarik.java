package edu.aptasinskij.jdbc.yarick;

import edu.aptasinskij.jdbc.DatabaseProperties;
import edu.aptasinskij.jdbc.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainYarik {

    public static void main(String[] args) throws SQLException {

        Connection connection1 = DriverManager.getConnection(DatabaseProperties.DATABASE_URL, DatabaseProperties.USERNAME, DatabaseProperties.SECRET);
        Statement statement = connection1.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Long id = resultSet.getLong("id");

            User user = new User(id,username,password);
            users.add(user);
            System.out.println(user);
        }
        resultSet.close();
        statement.close();
        connection1.close();


    }


}
