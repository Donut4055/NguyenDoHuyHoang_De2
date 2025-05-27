package com.example.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class Department {

    private int departmentId;
    @NotBlank
    private String departmentName;
    private String decscription;
    private int status;

    public Department() {
    }

    public Department(int departmentId, String departmentName, String decscription, int status) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.decscription = decscription;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public String getDecscription() {
        return decscription;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDecscription(String decscription) {
        this.decscription = decscription;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
