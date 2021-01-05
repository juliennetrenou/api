package com.dollar.api.services;

import com.dollar.api.models.Employee;
import com.dollar.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee saveEmployee(Employee employee){
                employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee signIn(String email){
        return employeeRepository.employeeExist(email);
    }

    public Long nbEmployee(){
        return employeeRepository.count();
    }

    public List<Employee> allEmployee(){
        return employeeRepository.findAll();
    }
}
