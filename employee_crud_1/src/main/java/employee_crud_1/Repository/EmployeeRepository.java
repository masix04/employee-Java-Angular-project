/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// STEP-2

package employee_crud_1.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee_crud_1.Model.Employee;
/**
 *
 * @author muhammad.ahmed
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
