package Jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//import Jdbc.Employee;

//@WebServlet("/employee") // Uncomment to enable servlet mapping in annotation
public class Infor extends HttpServlet {

    private final Employee empdet = new Employee();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String result = "";

        switch (action) {
            case "add":
                String name = request.getParameter("name");
                int age = Integer.parseInt(request.getParameter("age"));
                String department = request.getParameter("Department");
                result = empdet.addEmployee(name, age, department);
                break;
            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                String updateName = request.getParameter("name");
                int updateAge = Integer.parseInt(request.getParameter("age"));
                String updateDepartment = request.getParameter("department");
                result = empdet.updateEmployee(id, updateName, updateAge, updateDepartment);
                break;
            case "delete":
                int idToDelete = Integer.parseInt(request.getParameter("id"));
                result = Employee.deleteEmployee(idToDelete); // Call delete method
                break;
            default:
                result = "Invalid action.";
        }

        response.setContentType("text/html");
        response.getWriter().write(result);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String result = "";

        if ("view".equals(action)) {
            result = Employee.getAllEmployees();
        } else {
            result = "Invalid action.";
        }

        response.setContentType("text/html");
        response.getWriter().write(result);
    }
}
