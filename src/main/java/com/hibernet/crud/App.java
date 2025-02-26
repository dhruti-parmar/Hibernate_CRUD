package com.hibernet.crud;

import com.hibernate.DAO.EmployeeDAO;
import com.hibernate.model.Employee;
import com.hibernate.model.Address;
import com.hibernate.model.Department;
import com.hibernate.DAO.DepartmentDAO;
import com.hibernate.model.Project;
import com.hibernate.DAO.ProjectDAO;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ProjectDAO projectDAO = new ProjectDAO();

        createEmployee(employeeDAO, departmentDAO, projectDAO);

        readEmployees(employeeDAO);

        updateEmployee(employeeDAO);

        deleteEmployee(employeeDAO);
    }

    //Create Employee
    private static void createEmployee(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, ProjectDAO projectDAO) {
        Department department = new Department();
        department.setName("IT");
        departmentDAO.saveDepartment(department);

        Project project1 = new Project();
        project1.setProjectName("E-Commerce System");
        projectDAO.saveProject(project1);

        Employee employee = new Employee();
        employee.setName("Ronak");
        employee.setEmail("Ronak@example.com");
        employee.setDepartment(department);

        Address address = new Address();
        address.setCity("New York");
        address.setState("NY");
        employee.setAddress(address);

        Set<Project> projects = new HashSet<>();
        projects.add(project1);
        employee.setProjects(projects);

        employeeDAO.saveEmployee(employee);
        System.out.println("Employee Created!");
    }

    //Read Employees
    private static void readEmployees(EmployeeDAO employeeDAO) {
        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("------ Employee List ------");
        for (Employee emp : employees) {
            System.out.println(emp.getId() + ": " + emp.getName() + " - " + emp.getEmail());
        }
    }

    //Update Employee
    private static void updateEmployee(EmployeeDAO employeeDAO) {
        Long employeeId = 1L; 
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if (employee != null) {
            employee.setName("Dhruti Parmar");
            employee.setEmail("dhruti@example.com");

            Address newAddress = new Address();
            newAddress.setCity("Ahmedabad");
            newAddress.setState("Gujarat");
            employee.setAddress(newAddress);

            employeeDAO.updateEmployee(employee);
            System.out.println("Employee Updated!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    //Delete Employee
    private static void deleteEmployee(EmployeeDAO employeeDAO) {
        Long employeeId = 3L;
        employeeDAO.deleteEmployee(employeeId);
        System.out.println("Employee Deleted!");
    }
}
