package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeDAO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void addEmployee( String fullName, String email, String phoneNumber, String avtUrl, Date createAT, int departmentId) {
        Employee employee = new Employee();
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setAvtUrl(avtUrl);
        employee.setCreatedAt(createAT);
        employee.setDepartmentId(departmentId);
        employeeDAO.save(employee);
    }

    @Override
    public void removeEmployee(int id) {
        employeeDAO.delete(id);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employee = employeeDAO.findAll();
        return employee;
    }

    @Override
    public void updateEmployee(int id, String fullName, String email, String phoneNumber, String avtUrl, Date createAT, int departmentId) {
        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setAvtUrl(avtUrl);
        employee.setCreatedAt(createAT);
        employee.setDepartmentId(departmentId);

        employeeDAO.update(employee);
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employees = employeeDAO.searchByName(name);
        return employees;
    }

    @Override
    public Employee findById(int id) {
        List<Employee> employees = employeeDAO.findAll();
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return employee;
            }
        }
        return null;
    }
}

