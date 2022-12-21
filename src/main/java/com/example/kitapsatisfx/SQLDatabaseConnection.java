package com.example.kitapsatisfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {

    private String connectionUrl = "jdbc:sqlserver://localhost:1433;database=KitapSatis;user=sa;password=YamanLucy123;encrypt=true;trustServerCertificate=true";

    private Connection connection;

    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(connectionUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static void main(String[] args) {
        String connectionUrl =
                //jdbc:jtds:sqlserver://localhost:1433;domain=DEVELOPMENT;instance=ms_sql;databaseName=KitapSatis;user=SA;password=Admin@kub123
                "jdbc:jtds:sqlserver://localhost:1433;"
                        + "domain=DEVELOPMENT"
                        + "instance=ms_sql;"
                        + "databaseName=KitapSatis;"
                        + "user=SA;"
                        + "password=Admin@kub123;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;
        Connection connection = null;



        try {
            connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from Deneme";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }*/
}