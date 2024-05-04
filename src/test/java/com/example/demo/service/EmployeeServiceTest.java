package com.example.demo.service;

import Model.Employee;
import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import service.EmployeeService;
import service.EmployeeServiceImp;

import javax.naming.InvalidNameException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    private EmployeeService service = new EmployeeServiceImp();

    @Test
    public void shouldAddNewEmployee (){

        Employee employee = service.add("Vlad", "Efimov", 11111 );
        assertEquals("Vlad", employee.getFirstName());
        assertEquals("Efimov", employee.getLastName());
        assertEquals(11111, employee.getSalary());
        assertEquals(service.findEmployee("Vlad", "Efimov"), employee);


    }
    @Test
    public void shouldThrowExceptionsWhenNameIsNotNumeric(){
        assertThrows(InvalidNameException.class, () -> service.add ("10", "Efimov", 11111 ));
        assertThrows(InvalidNameException.class, () -> service.add ("Vlad", "8", 11111 ));
    }
    @Test
    public void shouldThrowExceptionsWhenAddingEmployee(){
        service.add("Vlad", "Efimov", 11111);

        assertThrows(EmployeeAlreadyAddedException.class, ()-> service.add("Vlad", "Efimov", 11111));
    }

    @Test
    public void shouldFindEmployee (){
        service.add("Vlad", "Efimov", 11111);
        Employee employee = service.findEmployee("Vlad", "Efimov");

        assertEquals("Vlad", employee.getFirstName());
        assertEquals("Efimov", employee.getLastName());
        assertEquals(11111, employee.getSalary());
    }

    @Test
    public void shouldThrowExceptionsWhenEmployeeNotExist (){
        assertThrows(EmployeeNotFoundException.class, ()-> service.findEmployee("Vlad", "Efimov"));
    }

    @Test
    public void shouldRemoveEmployee (){
        service.add("Vlad", "Efimov", 11111 );

        service.removeEmployee("Vlad", "Efimov");

        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Vlad", "Efimov"));

    }

    @Test
    public void shouldThrowExceptionsDeletingNonExistingEmployee (){

        assertThrows(EmployeeNotFoundException.class, () -> service.removeEmployee("Vlad", "Efimov"));
    }

    @Test
    public void shouldReturnAllEmployee (){
        Employee addedEmployee = service.add("Vlad", "Efimov", 11111);
        Collection<Employee> employees = service.findAllEmployees();
        assertEquals(1, employees.size());

        assertEquals(addedEmployee, employees.stream().findFirst().get());

    }
}
