package com.example.repository;



import com.example.model.Employee;
import com.example.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


@Repository
public class EmployeeDAO {

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "{call get_all_employees()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Employee p = new Employee();
                p.setEmployeeId(rs.getInt("employee_id"));
                p.setFullName(rs.getString("fullname"));
                p.setEmail(rs.getString("email"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setAvtUrl(rs.getString("avatar_url"));
                p.setStatus(rs.getInt("status"));
                p.setCreatedAt(rs.getDate("created_at"));
                p.setDepartmentId(rs.getInt("employee_id"));
                list.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void save(Employee employee) {
        String sql = "{call add_employee(?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, employee.getFullName());
            cs.setString(2, employee.getEmail());
            cs.setString(3, employee.getPhoneNumber());
            cs.setString(4, employee.getAvtUrl());
            cs.setInt(5, employee.getDepartmentId());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void update(Employee employee) {
        String sql = "{call edit_employee(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, employee.getEmployeeId());
            cs.setString(2, employee.getFullName());
            cs.setString(3, employee.getEmail());
            cs.setString(4, employee.getPhoneNumber());
            cs.setString(5, employee.getAvtUrl());
            cs.setInt(6, employee.getDepartmentId());

            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "{call delete_employee(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Employee> searchByName(String name) {
        List<Employee> list = new ArrayList<>();
        String sql = "{call search_employees_ny_name(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, name);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Employee d = new Employee();
                    d.setEmployeeId(rs.getInt("employee_id"));
                    d.setFullName(rs.getString("fullname"));
                    d.setEmail(rs.getString("email"));
                    d.setPhoneNumber(rs.getString("phone_number"));
                    d.setAvtUrl(rs.getString("avatar_url"));
                    d.setStatus(rs.getInt("status"));
                    d.setCreatedAt(rs.getDate("created_at"));
                    d.setDepartmentId(rs.getInt("department_id"));
                    list.add(d);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Employee findById(int id) {
        String sql = "{call find_employee_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Employee d = new Employee();
                    d.setEmployeeId(rs.getInt("employee_id"));
                    d.setFullName(rs.getString("fullname"));
                    d.setEmail(rs.getString("email"));
                    d.setPhoneNumber(rs.getString("phone_number"));
                    d.setAvtUrl(rs.getString("avatar_url"));
                    return d;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}


