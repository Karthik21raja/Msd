package Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

    // Constructor for Employee class
    public Employee() {}

    // Method to add a new employee to the database
    public String addEmployee(String name, int age, String department) {
        String sql = "INSERT INTO employee (name, age, department) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionsJdbc.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, department);
            int data = stmt.executeUpdate();
            return data > 0 ? "Employee added successfully" : "Failed to add employee";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Method to retrieve details of all employees
    public static String getAllEmployees() {
        String sql = "SELECT * FROM employee";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = ConnectionsJdbc.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                    .append(" | Name: ").append(rs.getString("name"))
                    .append(" | Age: ").append(rs.getInt("age"))
                    .append(" | Department: ").append(rs.getString("department"))
                    .append("<br>");
            }
            return sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Method to update employee details
    public String updateEmployee(int id, String name, int age, String department) {
        String sql = "UPDATE employee SET name = ?, age = ?, department = ? WHERE id = ?";
        try (Connection conn = ConnectionsJdbc.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, department);
            stmt.setInt(4, id);
            int data = stmt.executeUpdate();
            return data > 0 ? "Employee updated successfully" : "Failed to update employee";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Method to delete an employee
    public static String deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = ConnectionsJdbc.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? "Employee deleted successfully" : "Failed to delete employee";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
