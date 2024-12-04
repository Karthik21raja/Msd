package tripa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

class JobDetails {
    private List<String> jobTitles;

    public JobDetails(List<String> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public List<String> getJobTitles() {
        return jobTitles;
    }

    @Override
    public String toString() {
        return jobTitles.toString();
    }
}

class Department {
    private Map<String, JobDetails> departmentDetails;

    public Department() {
        this.departmentDetails = new LinkedHashMap<>();
    }

    public void addJobTitle(String departmentName, JobDetails jobDetails) {
        departmentDetails.put(departmentName, jobDetails);
    }

    public Map<String, JobDetails> getDepartmentDetails() {
        return departmentDetails;
    }

    @Override
    public String toString() {
        return departmentDetails.toString();
    }
}

class Employee {
    private String name;
    private String gender;
    private String designation;
    private Department departmentDetails;

    public Employee(String name, String gender, String designation) {
        this.name = name;
        this.gender = gender;
        this.designation = designation;
        this.departmentDetails = new Department();
    }

    public void addJobTitles(String departmentName, JobDetails jobDetails) {
        this.departmentDetails.addJobTitle(departmentName, jobDetails);
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDesignation() {
        return designation;
    }

    public Department getDepartmentDetails() {
        return departmentDetails;
    }

    @Override
    public String toString() {
        return "{ Name: " + name + ", Gender: " + gender + ", Designation: " + designation + ", Departments: " + departmentDetails + " }";
    }
}

public class EmployeeManagement {
    private static final String JSON_FILE = "employees.json";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Employee> employees = loadDataFromJsonFile();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new employee");
            System.out.println("2. Display all employee details");
            System.out.println("3. Sort employees by name");
            System.out.println("4. Find employees by job title");
            System.out.println("5. View department-wise employee count");
            System.out.println("6. View employee details by designation");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(s.nextLine());

            switch (choice) {
                case 1:
                    addEmployee(s, employees);
                    writeDataToJsonFile(employees);
                    break;
                case 2:
                    System.out.println("\nAll Employee Details:");
                    employees.forEach(System.out::println);
                    break;
                case 3:
                    employees.sort(Comparator.comparing(Employee::getName));
                    System.out.println("\nSorted Employees by Name:");
                    employees.forEach(System.out::println);
                    break;
                case 4:
                    findEmployeesByJobTitle(s, employees);
                    break;
                case 5:
                    viewEmployeeCountByDepartment(employees);
                    break;
                case 6:
                    viewEmployeesByDesignation(s, employees);
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner s, List<Employee> employees) {
        System.out.println("Enter details for the new employee:");
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Gender: ");
        String gender = s.nextLine();
        System.out.print("Designation: ");
        String designation = s.nextLine();

        Employee employee = new Employee(name, gender, designation);

        System.out.print("Enter number of departments for " + name + ": ");
        int numDepartments = Integer.parseInt(s.nextLine());

        for (int i = 1; i <= numDepartments; i++) {
            while (true) {
                System.out.print("Enter job titles for department " + i );
                String[] jobTitlesInput = s.nextLine().split(",");
                if (jobTitlesInput.length == 0) {
                    System.out.println("Error: You must enter at least one job title.");
                    continue;
                }
                List<String> jobTitles = new ArrayList<>(Arrays.asList(jobTitlesInput));
                employee.addJobTitles("Department " + i, new JobDetails(jobTitles));
                break;
            }
        }
        employees.add(employee);
    }

    private static List<Employee> loadDataFromJsonFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(JSON_FILE)) {
            Type employeeListType = new TypeToken<List<Employee>>() {}.getType();
            return gson.fromJson(reader, employeeListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private static void writeDataToJsonFile(List<Employee> employees) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            gson.toJson(employees, writer);
            System.out.println("\nEmployee data has been successfully written to '" + JSON_FILE + "'");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void findEmployeesByJobTitle(Scanner s, List<Employee> employees) {
        System.out.print("\nEnter job title to search employees: ");
        String jobTitle = s.nextLine();
        employees.stream()
                .filter(employee -> employee.getDepartmentDetails().getDepartmentDetails()
                        .values().stream()
                        .flatMap(jobDetails -> jobDetails.getJobTitles().stream())
                        .anyMatch(title -> title.equalsIgnoreCase(jobTitle)))
                .forEach(System.out::println);
    }

    private static void viewEmployeeCountByDepartment(List<Employee> employees) {
        Map<String, Integer> departmentCount = new HashMap<>();
        employees.forEach(employee -> employee.getDepartmentDetails().getDepartmentDetails()
                .keySet().forEach(department -> 
                        departmentCount.put(department, departmentCount.getOrDefault(department, 0) + 1)
                ));
        
        departmentCount.forEach((department, count) -> {
            System.out.println("Department: " + department + " | Employee Count: " + count);
        });
    }

    private static void viewEmployeesByDesignation(Scanner s, List<Employee> employees) {
        System.out.print("\nEnter designation to filter employees: ");
        String designation = s.nextLine();
        employees.stream()
                .filter(employee -> employee.getDesignation().equalsIgnoreCase(designation))
                .forEach(System.out::println);
    }
}
