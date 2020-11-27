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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@ResponseBody
@Api(
        value = "Api",
        description = "operation to manage user"
        )
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    @ApiOperation(
            value = "save employee in base",
            httpMethod = "POST",
            notes = "create new user",
            authorizations ={@Authorization(value = "apiKey")}
    )
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(
                new ApiResponse("User registered successfully !", true),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-in/{email}/{password}")
    @ApiOperation(
            value = "sign in ",
            httpMethod = "POST",
            notes = "employee sign in",
            authorizations ={@Authorization(value = "apiKey")}
    )
    public ResponseEntity<?> signInEmployee(@PathVariable String email, @PathVariable String password){

        if(employeeService.signIn(email,password).equals(true)){
            return new ResponseEntity<>(
                    new ApiResponse("Employee exist !", true),
                    HttpStatus.OK
            );

        }else {
            return new ResponseEntity<>(
                    new ApiResponse("wrong !", false),
                    HttpStatus.BAD_REQUEST
            );

        }
    }

}
