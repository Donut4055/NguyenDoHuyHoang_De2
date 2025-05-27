package com.example.controller;

import com.example.model.Department;
import com.example.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/departments")
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("department", departments);
        return "department-list";
    }

    @GetMapping("/departments/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping("/departments/add")
    public String saveDepartment(Department department) {
        departmentService.addDepartment(department.getDepartmentName(), department.getDecscription(), department.getStatus());
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{departmentId}")
    public String showEditForm( Model model, @PathVariable String departmentId) {
        Department department = departmentService.findById(Integer.parseInt(departmentId));
        model.addAttribute("department", department);
        return "department-form";
    }
    @PostMapping("/departments/edit/{departmentId}")
        public String updateDepartment(@PathVariable int departmentId,
        @ModelAttribute("department") @Valid Department department,
        BindingResult result) {
            if (result.hasErrors()) {
                return "department-form";
            }
        departmentService.updateDepartment(department.getDepartmentId(), department.getDepartmentName(), department.getDecscription(), department.getStatus());
        return "redirect:/departments";
    }

    @GetMapping("/departments/delete/{departmentId}")
    public String deleteDepartment(@PathVariable int departmentId) {
        departmentService.removeDepartment(departmentId);
        return "redirect:/departments";
    }

    @GetMapping("/departments/search")
    public String searchDepartments(@RequestParam("name") String name, Model model) {
        List<Department> departments = departmentService.findByName(name);
        model.addAttribute("department", departments);
        return "department-list";
    }
}
