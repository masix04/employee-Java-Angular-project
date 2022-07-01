/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author muhammad.ahmed
 */
public class sql_function {

    
    public boolean insert_to_db(String table_name, String column_names, String values, Connection conn) throws SQLException {
        
        String query = "INSERT into `"+table_name+"` ("+column_names+") VALUES("+values+") "; 
        System.out.println("INSERT Query:           "+query);
//        System.out.println(" SENT CONNECTION     :"+conn);

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.execute();
        
        return true;
    }
    
    public boolean delete_from_db(String table_name, String whereClause, Connection conn) throws SQLException {
        
        String query = "DELETE FROM "+table_name+" "+whereClause;
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.execute();
        
        return true;
    }
    
    public Object basic_select_to_db(String query, Connection conn) throws SQLException {
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        return rs;
    }
    
    public HashMap<String, String> select_from_db(String query) {
        String[] data = {"Employee", "Manager", "Cheif Officer Executive"};
        HashMap<String, String> json_data = new HashMap<String, String>();
        
        json_data.put("Employee_id", "21");
        json_data.put("Employee_id", "1");
        json_data.put("Employee_id", "2");
        json_data.put("Employee_id", "3");

        return json_data;
    }
    
    public boolean edit_in_db(String table_name, String column1, String column2, String value1, String value2, String whereClause, Connection conn) throws SQLException {
        String query = "UPDATE "+table_name+" SET "+column1+" = '"+value1+"' ,"+column2+" = '"+value2+"' "+whereClause;
        
        System.out.println(query);
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
        
        return true;
    }
}
