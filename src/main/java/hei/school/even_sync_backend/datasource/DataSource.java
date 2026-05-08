package hei.school.even_sync_backend.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/event_sync",
                    "postgres",
                    "azerty"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion : " + e.getMessage());
        }
    }
}