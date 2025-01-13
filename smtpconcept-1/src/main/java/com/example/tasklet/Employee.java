package com.example.tasklet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Employee {
	private String Name;
	private String Gmail;
	private long Ph_No;
	private String Status;

	public Employee(String Name, int Age, String Gender, long  Ph_No, String Status ) {
		this.Name = Name;
		this.Gmail = Gmail;
		this.Ph_No = Ph_No;
		this.Status = Status;
	}

	
	public void viewEmployee() {
        String SelectQuery = "Select * from new";
        try (Connection conn = ConnectionsJdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SelectQuery)) {
            ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
                
                   
                    System.out.println(rs.getString("Name"));
                    System.out.println(rs.getString("Gmail"));
                    System.out.println(rs.getLong("Ph_No"));
                    System.out.println(rs.getString("Status"));
                
            }
			else {
				System.out.println("Not Completed : Something is Wrong");
			}
			conn.close();

           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


