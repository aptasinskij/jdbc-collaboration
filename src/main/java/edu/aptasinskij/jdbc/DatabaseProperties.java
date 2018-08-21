package edu.aptasinskij.jdbc;

public class DatabaseProperties {

    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/collaboration?useSSL=false&serverTimezone=UTC";

    public static final String USERNAME = "root";

    public static final String SECRET = "digitalNight";

    private DatabaseProperties() {
        throw new UnsupportedOperationException("Utility class");
    }


}
