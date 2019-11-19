package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Csatlakozás az adatbázishoz */

public class DBConnector {
    public static final String URL = "jdbc:sqlite:rssreader.sqlite";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
