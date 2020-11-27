package com.dollar.api.repositories;

import com.dollar.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(
            value = "select id from user where email = :email and password = :password",
            nativeQuery = true
    )
    public Boolean employeeExist(@Param("email") String email, @Param("password") String password );
}
