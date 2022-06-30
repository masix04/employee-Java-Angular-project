/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// STEP 3

package employee_crud_1.Service;

import employee_crud_1.Model.Employee;
import employee_crud_1.Repository.EmployeeRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad.ahmed
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;
    
    //CREATE
    public Employee createEmployee(Employee emp) {
        return empRepository.save(emp);
    }
    
    // READ
    public List<Employee> getEmployees() {
        return empRepository.findAll();
    }
    
    // DELETE
    public void deleteEmployee(Integer empNo) {
        empRepository.deleteById(empNo);
    }
    
    // UPDATE
    public Employee upldateEmployee(Integer empNo, Employee employeeDetails) {
        Employee emp = empRepository.findById(empNo).get();
        emp.setFirstName(employeeDetails.getFirstName());
        emp.setLastName(employeeDetails.getLastName());
        emp.setEmpTitle(employeeDetails.getEmpTitle());
        emp.setBirthDate(employeeDetails.getBirthDate());
        emp.setHireDate(employeeDetails.getHireDate());
        emp.setGender(employeeDetails.getGender());
        
        return empRepository.save(emp);
    }
}
