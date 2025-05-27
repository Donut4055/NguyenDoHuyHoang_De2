package com.example.controller;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employee", employees);
        return "employee-list";
    }

    @GetMapping("/employees/add")
    public String showAddForm(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());

        return "employee-form";
    }

    @PostMapping("/employees/add")
    public String saveEmployee(Employee employee) {
        employeeService.addEmployee(employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(),
                employee.getAvtUrl(), employee.getCreatedAt(), employee.getDepartmentId());

        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{employeeId}")
    public String showEditForm( Model model, @PathVariable String employeeId) {
        Employee employee = employeeService.findById(Integer.parseInt(employeeId));
        model.addAttribute("employee", employee);
        return "employee-form";
    }
    @PostMapping("/employees/edit/{employeeId}")
    public String updateEmployee(@PathVariable int employeeId,
                                   @ModelAttribute("employee") @Valid Employee employee,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        employeeService.updateEmployee(employee.getEmployeeId(), employee.getFullName(), employee.getEmail(),
                employee.getPhoneNumber(), employee.getAvtUrl(), employee.getCreatedAt(), employee.getDepartmentId());
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        employeeService.removeEmployee(employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam("name") String name, Model model) {
        List<Employee> employees = employeeService.findByName(name);
        model.addAttribute("employee", employees);
        return "employee-list";
    }
}

