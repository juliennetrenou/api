package com.dollar.api.controllers;

import com.dollar.api.models.Employee;
import com.dollar.api.response.ApiResponse;
import com.dollar.api.services.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@ResponseBody
@Api(
        value = "Api",
        description = "operation to manage user"
)
public class EmployeeController {

    private EmployeeService employeeService;
    PasswordEncoder passwordEncoder;


    @Autowired
    public EmployeeController(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/save")
    @ApiOperation(
            value = "save employee in base",
            httpMethod = "POST",
            notes = "create new user",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(
                new ApiResponse("User registered successfully !", true),
                HttpStatus.OK
        );
    }

    @GetMapping("/sign-in/{email}/{password}")
    @ApiOperation(
            value = "sign in ",
            httpMethod = "GET",
            notes = "employee sign in",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public Employee signInEmployee(@PathVariable String email, @PathVariable String password) {

        System.out.println(email);
        System.out.println(password);

        //System.out.println(employeeService.signIn(email, password));;

        Employee employee = employeeService.signIn(email);
        System.out.println(employee.getName());
        if (employeeService.signIn(email) != null && passwordEncoder.matches(password,employee.getPassword())) {
            return employee;

            /*return new ResponseEntity<>(
                    new ApiResponse("Employee exist !", true),
                    HttpStatus.OK
            );*/

        } else {
            return null;
            /*new ResponseEntity<>(
                    new ApiResponse("wrong !", false),
                    HttpStatus.BAD_REQUEST
            );
*/
        }
    }

    @GetMapping("/nbEmployee")
    @ApiOperation(
            value = "get nb employee",
            httpMethod = "GET",
            notes = "nb of employee in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public Long nbEmployee(){
        return employeeService.nbEmployee();
    }

    @GetMapping("/allEmployee")
    @ApiOperation(
            value = "get all employee",
            httpMethod = "GET",
            notes = "all employee in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public List<Employee> allEmployee(){
        return employeeService.allEmployee();
    }
}
