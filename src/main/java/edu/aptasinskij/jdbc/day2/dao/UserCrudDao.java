package edu.aptasinskij.jdbc.day2.dao;

import edu.aptasinskij.jdbc.DatabaseProperties;
import edu.aptasinskij.jdbc.User;

import java.sql.*;

/**
 * DAO implementation for @see {@link User} with identifier @see {@link Long}
 * */
public class UserCrudDao implements CrudDao<User, Long> {

    // SQL for inserting new user record
    private static final String CREATE_USER_SQL = "INSERT INTO users (username, password) VALUES (?, ?)";

    //SQL for selecting user record by id
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = ? LIMIT 1";

    //just error message to avoid code duplication
    private static final String EXCEPTION_MESSAGE = "Exception...";

    //utility method for Connection retrieval
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseProperties.DATABASE_URL, DatabaseProperties.USERNAME, DatabaseProperties.SECRET);
    }

    /*
    * Create user method implementation
    * */
    @Override
    public void create(User entity) {

        try (Connection connection = getConnection(); // open new connection
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL)) { //prepare new statement (actual SQL passed to DB at this point)
            connection.setAutoCommit(false); //disabling auto-committing feature of JDBC
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.execute(); //actual execution of statement
            connection.commit(); //manual connection committing, due to disabling, see above
        } catch (SQLException e) {
            System.out.println(EXCEPTION_MESSAGE);
        }
    }

    @Override
    public User select(Long id) {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            connection.setAutoCommit(false); //disabling auto-committing feature of JDBC
            resultSet = preparedStatement.executeQuery(); //actual query execution
            /*resultSet.setFetchSize(1); //if the was multiple records satisfying the SQL, trim them to size of 1*/
            if (resultSet.next()) { //if result contains any record
                //read all the values
                long identifier = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                /**
                 * construct and return new user object, @see {@link User#fromDatabaseRecord(Long, String, String)}
                 * */
                return User.fromDatabaseRecord(identifier, username, password);
            }
            connection.commit(); //manual committing
        } catch (SQLException e) {
            System.out.println(EXCEPTION_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close(); //if result set was successfully created, close it
            } catch (SQLException e) {
                System.out.println(EXCEPTION_MESSAGE);
            }
        }
        return null; //means the SQL returns empty ResultSet
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
