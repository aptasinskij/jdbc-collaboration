package edu.aptasinskij.jdbc.peter;

import edu.aptasinskij.jdbc.DatabaseProperties;
import edu.aptasinskij.jdbc.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainPeter {

    public static void main(String[] args) throws Exception {
        Connection connect = DriverManager.getConnection(DatabaseProperties.DATABASE_URL, DatabaseProperties.USERNAME, DatabaseProperties.SECRET);
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");

        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            long id = resultSet.getLong("id");
            User user = new User(id, username, password);
            users.add(user);
            System.out.println(user);
        }
        System.out.println(users.size());
        resultSet.close();
        statement.close();
        connect.close();

    }

}
