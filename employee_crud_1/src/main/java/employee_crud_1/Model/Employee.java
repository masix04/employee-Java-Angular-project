/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_crud_1.Model;
import java.sql.Date;

import javax.persistence.*;

/**
 *
 * @author muhammad.ahmed
 */
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id // means primary key Identifier
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
        private int emp_no;
    @Column(name="first_name")
        private String first_name;
    @Column(name="last_name")
        private String last_name;
    @Column(name="emp_title")
        private String emp_title;
    @Column(name="birth_date")
        private Date birth_date;
    @Column(name="hire_date")
        private Date hire_date;
    @Column(name="gender")
        private String gender;

    /**
     * @return the emp_no
     */
    public int getEmpNo() {
        return emp_no;
    }

    /**
     * @param emp_no the emp_no to set
     */
    public void setEmpNo(int emp_no) {
        this.emp_no = emp_no;
    }

    /**
     * @return the first_name
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the emp_title
     */
    public String getEmpTitle() {
        return emp_title;
    }

    /**
     * @param emp_title the emp_title to set
     */
    public void setEmpTitle(String emp_title) {
        this.emp_title = emp_title;
    }

    /**
     * @return the birth_date
     */
    public Date getBirthDate() {
        return birth_date;
    }

    /**
     * @param birth_date the birth_date to set
     */
    public void setBirthDate(Date birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * @return the hire_date
     */
    public Date getHireDate() {
        return hire_date;
    }

    /**
     * @param hire_date the hire_date to set
     */
    public void setHireDate(Date hire_date) {
        this.hire_date = hire_date;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
