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
        try (Connection conn = ConnectionsJdbc.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    // Method to retrieve details of all employees as an HTML table
    public static String getAllEmployees() {
        String sql = "SELECT * FROM employee";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = ConnectionsJdbc.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            
            sb.append("<table border='1' style='width:100%; text-align:center;'>")
              .append("<tr>")
              .append("<th>ID</th>")
              .append("<th>Name</th>")
              .append("<th>Age</th>")
              .append("<th>Department</th>")
              .append("<th>Actions</th>")
              .append("</tr>");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                sb.append("<tr>")
                  .append("<td>").append(id).append("</td>")
                  .append("<td>").append(rs.getString("name")).append("</td>")
                  .append("<td>").append(rs.getInt("age")).append("</td>")
                  .append("<td>").append(rs.getString("department")).append("</td>")
                  .append("<td>")
                  .append("<form action='upda.html' style='display:inline;'>")
                  .append("<button type='submit'>Update</button>")
                  .append("</form>")
                  .append(" ")
                  .append("<form action='big' method='post' style='display:inline;'>")
                  .append("<input type='hidden' name='action' value='delete'>")
                  .append("<input type='hidden' name='id' value='").append(id).append("'>")
                  .append("<button type='submit'>Delete</button>")
                  .append("</form>")
                  .append("</td>")
                  .append("</tr>");
            }
            sb.append("</table>")
              .append("<br>")
              .append("<form action='inf.html' style='display:inline;'>")
              .append("<button type='submit'>Home</button>")
              .append("</form>")
              .append("<form action='adde.html' style='display:inline;'>")
              .append("<button type='submit'>Add New Employee</button>")
              .append("</form>");
            
            return sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Method to update employee details
    public String updateEmployee(int id, String name, int age, String department) {
        String sql = "UPDATE employee SET name = ?, age = ?, department = ? WHERE id = ?";
        try (Connection conn = ConnectionsJdbc.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = ConnectionsJdbc.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? "Employee deleted successfully" : "Failed to delete employee";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

	public ResultSet getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
