package com.example.service;

import com.example.model.Department;
import com.example.repository.DepartmentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public void addDepartment(String name, String description, int status) {
        Department department = new Department();
        department.setDepartmentName(name);
        department.setDecscription(description);
        department.setStatus(status);
        departmentDAO.save(department);
    }

    @Override
    public void removeDepartment(int id) {
        departmentDAO.delete(id);
    }

    @Override
    public List<Department> findAll() {
        List<Department> department = departmentDAO.findAll();
        return department;
    }

    @Override
    public void updateDepartment(int id, String name, String description, int status) {
        Department department = new Department();
        department.setDepartmentId(id);
        department.setDepartmentName(name);
        department.setDecscription(description);
        department.setStatus(status);
        departmentDAO.update(department);
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> departments = departmentDAO.searchByName(name);
        return departments;
    }

    @Override
    public Department findById(int id) {
        List<Department> departments = departmentDAO.findAll();
        for (Department department : departments) {
            if (department.getDepartmentId() == id) {
                return department;
            }
        }
        return null;
    }
}
