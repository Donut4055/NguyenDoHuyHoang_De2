package com.example.repository;




import com.example.model.Department;
import com.example.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


@Repository
public class DepartmentDAO {

    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        String sql = "{call get_all_departments()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Department p = new Department();
                p.setDepartmentId(rs.getInt("department_id"));
                p.setDepartmentName(rs.getString("department_name"));
                p.setDecscription(rs.getString("description"));
                p.setStatus(rs.getInt("status"));
                list.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void save(Department department) {
        String sql = "{call add_department(?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, department.getDepartmentName());
            cs.setString(2, department.getDecscription());
            cs.setInt(3, department.getStatus());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void update(Department department) {
        String sql = "{call edit_department(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, department.getDepartmentId());
            cs.setString(2, department.getDepartmentName());
            cs.setString(3, department.getDecscription());
            cs.setInt(4, department.getStatus());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "{call delete_department(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Department> searchByName(String name) {
        List<Department> list = new ArrayList<>();
        String sql = "{call search_departments_ny_name(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, name);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Department d = new Department();
                    d.setDepartmentId(rs.getInt("department_id"));
                    d.setDepartmentName(rs.getString("department_name"));
                    d.setDecscription(rs.getString("description"));
                    d.setStatus(rs.getInt("status"));
                    list.add(d);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Department findById(int id) {
        String sql = "{call find_department_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Department d = new Department();
                    d.setDepartmentId(rs.getInt("department_id"));
                    d.setDepartmentName(rs.getString("department_name"));
                    d.setDecscription(rs.getString("description"));
                    d.setStatus(rs.getInt("status"));
                    return d;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}

