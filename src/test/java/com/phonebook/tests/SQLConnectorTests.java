package com.phonebook.tests;

import com.phonebook.data.DbData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class SQLConnectorTests {

    Connection connection;

    @BeforeMethod
    public void loadDb() {
        //load mysql jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection to DB
            connection = DriverManager.getConnection(DbData.dbUrl, DbData.username,DbData.password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getDataTableTest() {
        //create Statement Object
        try {
            Statement statement = connection.createStatement();

            //execute SQL query. Store results in ResultsSet
            ResultSet resultSet = statement.executeQuery(DbData.querySelect);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void closeDb() {
        //closing DB connection
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteRowTest() {
        //modify Statement Object
        try {
            PreparedStatement statement = connection.prepareStatement(DbData.queryDelete);

            statement.executeUpdate();

            ResultSet resultSet = statement.executeQuery(DbData.querySelect);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
