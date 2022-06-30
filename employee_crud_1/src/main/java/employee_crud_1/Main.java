/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_crud_1;
import java.sql.SQLException;

import config.db_config;
import helpers.sql_function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author muhammad.ahmed
 */
@SpringBootApplication
public class Main {

    /**
     * @param args the command line arguments
     */
    private static Object create_connection;
    
    public static void main(String[] args) throws SQLException {
       
        SpringApplication.run(Main.class, args);
        // Create Connection by Calling Custom function from db_config.java
        db_config config_object = new db_config();
        config_object.db_config();

        sql_function DB = new sql_function();
        String select_query = "SELECT emp.* from `employees` as emp WHERE emp.`first_name` = 'Perry'";
        
        // getting resultSet Type Data in ResultSET Type Variable.
        ResultSet rs = (ResultSet) DB.basic_select_to_db(select_query, config_object.getConnection());
        
        
        DisplaySelectedData(rs);
        
          
        // Edit Query
        String whereClause = "WHERE YEAR(birth_date) = '2000' ";
        boolean edit_check = (boolean) DB.edit_in_db("employees", "first_name", "last_name", "abc", "xyz", whereClause, config_object.getConnection());
        if(edit_check == true) {
            System.out.println("Column Values have been Updated");
        }
        else {
            System.out.println("Sorry! Data has not been Updated");
        }
        
        checkSelect(DB, config_object.getConnection());
        
        // INSERT Query
        String table_name = "employees";
        String table_columns = "emp_no, emp_title, birth_date, first_name, last_name, gender, hire_date";
        String column_values = "3, 'e0007', '2001-10-30', 'Rica', 'Banance', 'F', '2019-11-30'";
        
        boolean insert_check = DB.insert_to_db(table_name, table_columns, column_values, config_object.getConnection());
        if(insert_check == true) {
            System.out.println("Data has inserted.");
        }
        else {
            System.out.println("Sorry! Data has not inserted");
        }
        
        checkSelect(DB, config_object.getConnection());
     
        
        // DELETE Query
        String deleteWhereClause = "WHERE emp_no = 1";
        boolean delete_check = DB.delete_from_db(table_name, deleteWhereClause, config_object.getConnection());
         if(delete_check == true) {
            System.out.println("Data has deleted.");
        }
        else {
            System.out.println("Sorry! Data has not deleted");
        }
         
        checkSelect(DB, config_object.getConnection());
    
    }
        // After Change in DB check the Whole Data,
            // This Function will repeatedly Work So I have cretaed  a Function for it.
    public static void checkSelect(sql_function DB, Connection conn) throws SQLException {
        String select_check_query = "SELECT * from employees";
        ResultSet rs_check = (ResultSet) DB.basic_select_to_db(select_check_query, conn);
        
        DisplaySelectedData(rs_check);
    }

    // Formatted and Display ResultSet Selected Data   After Select Query Ran AND Brings ResultSet
    public static void DisplaySelectedData(ResultSet rs) throws SQLException {
        
        ResultSetMetaData rsmd = rs.getMetaData();
        
        int columnsCount = rsmd.getColumnCount();
        System.out.println();
        while(rs.next()) {
           for (int i = 1; i <= columnsCount; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
//                if(columnValue == rs.getString(i) ) {
//                    System.out.print(" |=====>| "+rsmd.getColumnName(i)+ " => " +columnValue);
//                }
                System.out.print(" | "+rsmd.getColumnName(i)+ " => " +columnValue);
            }
           System.out.println("");
        }
        System.out.println();
    } 
}
