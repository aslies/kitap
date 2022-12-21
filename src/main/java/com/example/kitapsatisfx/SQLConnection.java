package com.example.kitapsatisfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    String url = "jdbc:sqlserver://localhost:1433;database=KitapSatis;user=sa;password=YamanLucy123;encrypt=true;trustServerCertificate=true";
    Connection connection  = DriverManager.getConnection(url);
    Statement statement = connection.createStatement();
    public SQLConnection() throws SQLException {
    }

    public Statement getStatement() {
        return statement;
    }


}
