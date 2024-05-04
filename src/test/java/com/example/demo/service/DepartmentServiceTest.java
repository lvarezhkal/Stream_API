package com.example.demo.service;

import Model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.DepartmentService;
import service.EmployeeService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService departmentService;
    @Mock
    private EmployeeService employeeService;

    private List<Employee> employees = List.of(new Employee("Vlad", "Efimov", 11111, 1), new Employee("Petr", "Ivanov", 32200, 1), new Employee("Ivan", "Ivanov", 14880, 2));

    @Test
    public void shouldReturnEmployeeWithMaxSalary() {

        when(employeeService.findAllEmployees()).thenReturn(employees);
        Employee employee = departmentService.findEmployeeMaxSalaryByDepartment(1);

        assertEquals("Petr", employee.getFirstName());
        assertEquals("Ivanov", employee.getLastName());
    }

    @Test
    public void shouldReturnEmployeeWithMInSalary() {

        when(employeeService.findAllEmployees()).thenReturn(employees);
        Employee employee = departmentService.findEmployeeMinSalaryByDepartment(1);

        assertEquals("Petr", employee.getFirstName());
        assertEquals("Ivanov", employee.getLastName());
    }

    @Test
    public void shouldReturnAllEmployeeGroupedByDepartment() {
        when(employeeService.findAllEmployees()).thenReturn(employees);

        Map<Integer, List<Employee>> result = departmentService.findEmployeeByDepartment();
        assertEquals(2, result.size());
        assertEquals(2, result.get(1).size());
        assertEquals(1, result.get(2).size());
    }
}
