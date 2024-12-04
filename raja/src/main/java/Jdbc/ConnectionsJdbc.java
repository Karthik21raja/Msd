package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionsJdbc {

//	private static final String URL = "jdbc:mysql://localhost:3306/blue";
//
//    public static Connection getConnection() throws SQLException {
//        Properties properties = new Properties();
//        properties.setProperty("user", "root");
//        properties.setProperty("password", "root");
//        properties.setProperty("SetFloatAndDoubleUseBinary", "true");
//
//        return DriverManager.getConnection(URL, properties);
//    }
	private static final String URL = "jdbc:mysql://localhost:3306/blue";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}