package com.example.service;

import com.example.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
    void addEmployee(String fullName, String email, String phoneNumber, String avtUrl, Date createAT, int departmentId );
    void removeEmployee(int id);
    List<Employee> findAll();
    void updateEmployee(int id, String fullName, String email, String phoneNumber, String avtUrl, Date createAT, int departmentId );
    List<Employee> findByName(String fullName);
    Employee findById(int id);

}

