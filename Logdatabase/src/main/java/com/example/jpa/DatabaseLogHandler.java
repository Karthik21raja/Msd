package com.example.jpa;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseLogHandler extends Handler {

    private Connection connection;

  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/logging_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public DatabaseLogHandler() {
        try {
            
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    @Override
    public void publish(LogRecord record) {
        if (!isLoggable(record)) {
            return;
        }

        
        String logMessage = record.getMessage();
        String logLevel = record.getLevel().getName();
        String loggerName = record.getLoggerName();

       
        String sql = "INSERT INTO logs (level, logger, message) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, logLevel);
            statement.setString(2, loggerName);
            statement.setString(3, logMessage);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to log to the database: " + e.getMessage());
        }
    }

   

    @Override
    public void close() throws SecurityException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection: " + e.getMessage());
        }
    }

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
}
