package com.example.projekt_psm;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class PostgreSQLJDBC {

   public String driver = "org.postgresql.Driver";
     String host = "195.150.230.208";
     String port = "5432";
     String dbname = "2023_osak_dawid";

    String user;
     String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;

     String pass;
     Connection connection;

    public PostgreSQLJDBC(String user, String pass) {
        this.user = user;
        this.pass = pass;

        connection = makeConnection();
    }

    public Connection getConnection() {
        return (connection);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Blad przy zamykaniu polaczenia: " + sqle);

        }
    }

    public Connection makeConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            return (connection);

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Blad ladowania sterownika: " + cnfe);

            return (null);
        } catch (SQLException sqle) {
            System.err.println("Blad przy nawiązywaniu polaczenia: " + sqle);

            return (null);
        }
    }
}

