package blue;

public class Main {
	 public static void main(String[] args) {
	        // Adding a new employee
	        Employee emp1 = new Employee("karthik", 21, "developer");
	        Employee emp2 = new Employee("dhoni", 22, "analyis");
	        Employee emp3 = new Employee("dhanush", 23, "creator");
	        emp1.addEmployee();
	        emp2.addEmployee();
	        emp3.addEmployee();

	        // Retrieving an employee by ID
	        Employee emp = Employee.getEmployeeById(1);
	        if (emp != null) {
	            System.out.println(emp);
	        }
	
	        // Updating employee details
	        emp1 = new Employee(1, "John Smith", 31, "Finance");
	        emp1.updateEmployee();
	
	        // Deleting an employee
	       // Employee.deleteEmployee(1);
	    }
	}