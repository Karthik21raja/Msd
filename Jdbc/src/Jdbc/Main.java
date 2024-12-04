package Jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Main {
	 public static void main(String[] args) throws Exception {

	    	Scanner s = new Scanner(System.in);

	    	int choice;

	    	do {

	    		System.out.println("Wellcome to my company");

	    		System.out.println("1.Adding a new employee");

	    

	    		System.out.println("2.Retrieving an employee by ID");

	    		System.out.println("3.Updating employee details");

	    		System.out.println("4.Deleting an employee");

	    	

	    		System.out.println("5.Exit");

	    		System.out.println("Enter Your choice");

	    		choice = s.nextInt();

	    		switch (choice) {

				case 1: {

					System.out.println("Enter the employee name");

					String name= s.next();

					System.out.println("Enter the employee age");

					int age = s.nextInt();

					System.out.println("Enter the employee department");

					String role = s.next();				

					 Employee emp1 = new Employee(name,age,role);

					 emp1.addEmployee();

					 System.out.println("");

					 Thread.sleep(1000);

					 break;

				}



				case 2:{

					System.out.println("Enter the employee id: ");

					int id =s.nextInt();

					Employee emp = Employee.getEmployeeById(id);

			        if (emp != null) {

			            System.out.println(emp);

			        }

			        System.out.println("");

			        Thread.sleep(1000);

					 break;

				}

				case 3:{

					System.out.println("Enter the employee id: ");

					int id =s.nextInt();

					System.out.println("Enter the employee name: ");

					String name= s.next();

					System.out.println("Enter the employee age: ");

					int age = s.nextInt();

					System.out.println("Enter the employee role: ");

					String role = s.next();				

					 Employee emp1 = new Employee(id,name,age,role);

					 emp1.updateEmployee();

					 System.out.println("");

					 Thread.sleep(1000);

					 break;

				}

				case 4:{

					System.out.println("Enter the employee id: ");

					int id =s.nextInt();

					Employee.deleteEmployee(id);

					System.out.println("");

				}

				System.out.println("");

				Thread.sleep(1000);

				break;

			

				case 5: {

					System.out.println("Exiting.......");

					System.exit(0);

				}

				default:

					throw new IllegalArgumentException("Unexpected value: " + choice);

				}

	    	}while(true);

	    	

	    }

	}