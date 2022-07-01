/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes;

import employee_crud_1.Main;

/**
 *
 * @author muhammad.ahmed
 */
// Added inheritence with Main
//public class Routes extends Main  {
 public class Routes {

    private String APIURL;
    private String getEmployees;
    
    // Setter & Getter of APIURL
    public void setApiUrl() {
        this.APIURL = "/api/";
    }
    public String getApiUrl() {
        return this.APIURL;
    }
    
    // Getter of GetEmployee
    public String getEmployeesRoute() {
        this.getEmployees = this.APIURL + "employees";
        return this.getEmployees;
    }
}
