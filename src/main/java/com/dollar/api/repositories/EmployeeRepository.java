package com.dollar.api.repositories;

import com.dollar.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(
            value = "select * from employee where email = :email",
            nativeQuery = true
    )
    Employee employeeExist(@Param("email") String email );
    //Employee employeeExist(@Param("email") String email, @Param("password") String password );

    Optional<Employee> findByEmail(String email);
}
