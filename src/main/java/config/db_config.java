/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author muhammad.ahmed
 */
public class db_config {
    
    boolean status = false;
    String Database = "employee_system";
    String BaseUrl = "jdbc:mysql://localhost:3310/"+Database;
    String username = "root";
    String password = "Root0)00"; // for Port 3310
//    String password = ""; // for Port 3306
    
    Connection conn;

    public Object db_config() throws SQLException {
        DriverManager DM = null;
        db_config is_connect = new db_config();

        try {
            
            this.conn = DM.getConnection(BaseUrl, username, password);
            System.out.println("Connection to '"+BaseUrl+"' has accomplished Successfully.");
            
            // pouring in is_connect Object
            is_connect.status = true; 
            is_connect.conn = conn;
            
            return is_connect;
        } catch(SQLException ex) {
            System.out.println("Connection to "+BaseUrl+" has not accomplished Successfully! ");
            
            // pouring in is_connect Object
            is_connect.status = false; 
            is_connect.conn = conn;
            Logger.getLogger(db_config.class.getName()).log(Level.SEVERE, null, ex);
            return is_connect;
        }
    }
    
   public String getAuthorization() {
       return this.password;
   }
    public Connection getConnection() {
        return conn;
    }
}
