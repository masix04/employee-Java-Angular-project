/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// STEP - 4

package employee_crud_1.APIController;
//import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import employee_crud_1.Model.Employee;
import employee_crud_1.Service.EmployeeService;
import java.util.List;

/**
 *
 * @author muhammad.ahmed
 */
@RestController // This means that this class is a REST Controller
@RequestMapping("/api") // Added a baseURL because of Common Word
public class Controller {
    
    @Autowired
    EmployeeService empService;
    
    @RequestMapping(value="/employees", method=RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.createEmployee(emp);
    }
    
    @RequestMapping(value="/employees/{empNo}", method=RequestMethod.PUT)
    public List<Employee> readEmployees(@PathVariable(value = "empNo") int emp_no, @RequestBody Employee empDetails) {
        return empService.getEmployees();
    }
    
    @RequestMapping(value="employees/{empNo}", method=RequestMethod.DELETE)
    public void deleteEmployees(@PathVariable(value="empNo") Integer emp_no) {
        empService.deleteEmployee(emp_no);
    }
}
