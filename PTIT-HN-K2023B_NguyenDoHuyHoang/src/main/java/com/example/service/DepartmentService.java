package com.example.service;

import com.example.model.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(String name, String description, int status);
    void removeDepartment(int id);
    List<Department> findAll();
    void updateDepartment(int id, String name, String description, int status);
    List<Department> findByName(String name);
    Department findById(int id);

}
