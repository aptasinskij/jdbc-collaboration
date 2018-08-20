package edu.aptasinskij.jdbc.yarick;

import edu.aptasinskij.jdbc.DatabaseProperties;

import java.sql.*;
import java.util.Scanner;

public class MainYarickInsert {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Connection connection = DriverManager.getConnection(DatabaseProperties.DATABASE_URL, DatabaseProperties.USERNAME, DatabaseProperties.SECRET);
        Statement statement = connection.createStatement();
        String inserUserSql = String.format("INSERT into users(username, password) value ('%s', '%s')", username, password);
        statement.execute(inserUserSql);
        statement.close();
        connection.close();
        scanner.close();
    }

}
