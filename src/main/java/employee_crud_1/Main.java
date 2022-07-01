/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_crud_1;

// 1st Step
import com.sun.net.httpserver.HttpServer;
import config.db_config;
import helpers.sql_function;

import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;


//import routes.Routes;
/**
 *
 * @author muhammad.ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static String APIURL = "/api/";
    public static void main(String[] args) throws IOException, SQLException {

        int serverPort = 8080;
        
        // Create Router Class
//        Routes apiRoutes = new Routes();
//        String getEmployees = apiRoutes.getEmployeesRoute();
        
        InetSocketAddress ISA = new InetSocketAddress(serverPort);
        // Frameless- pURE JAVA API
        HttpServer httpServer = HttpServer.create(ISA,0);
        
        
        Scanner inputAPINo = new Scanner(System.in);
        System.out.println("\n - 4 APIs to send Request \n 1- Get a Success Response."
                        + " \n 2- Get Greeting with name using Query Params. "
                        + " \n 3- API with param."
                        + " \n 4- not decieded Yet.");
        
        int apiId = inputAPINo.nextInt();
        System.out.println("Inputed : "+apiId);

        
        switch (apiId) {
            case 1:             send1stAPIRequest(httpServer); break;
            case 2:             send2ndAPIRequest(httpServer); break;
            case 3:             send3rdAPIRequest(httpServer); break;
            case 4:             send4thAPIRequest(httpServer); break;
        }
       
       /**
        *  NOTE: If you want to Send API Request 
        *                                       You need to Run the Project, then
        *               Open console and 
        *                           Run "CURL -X GET localhost:8080/api/your-api-name"
        */
       

        // Create a Default Executor
        httpServer.setExecutor(null);
        httpServer.start();
    }
      
    public static void send1stAPIRequest(HttpServer httpServer) throws SQLException {
       System.out.println("1st API Request Started.");

       sql_function DB = new sql_function();
         // 1st API to get Just message 
        httpServer.createContext(Main.APIURL + "employees", (exchange -> {
            
            try (exchange) {
                if("GET".equals(exchange.getRequestMethod())) {
                    
                    db_config config_object = new db_config();
                    config_object.db_config();
                    
                    String query = "Select * from employees";
                    ResultSet rs = (ResultSet) DB.basic_select_to_db(query, config_object.getConnection());
                    
                    DisplaySelectedData(rs);
//                    Map<String, String> DATA = FormatSelectedData(rs);
//                    System.out.println(DATA);
                    String responseText = "Success Getting Employees";

                    // Success Message
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);

                    OutputStream output = exchange.getResponseBody();
                    output.write(responseText.getBytes());
                    output.flush();
                }
                else {
                    // Failed Message
                    exchange.sendResponseHeaders(405, -1);
                }
            } catch (SQLException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
            exchange.close();
        }));
    }
    
    public static void send2ndAPIRequest(HttpServer httpServer) {
         System.out.println("2nd API Request Started.");
        
          // 2nd API to get Message with Name USING Query-Param
        httpServer.createContext(Main.APIURL + "greeting", (exchange -> {
            try (exchange) {
                if("GET".equals(exchange.getRequestMethod())) {
                    Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
                    System.out.println("RawQuery: "+exchange.getRequestURI().getRawQuery());
                    System.out.println("Query: "+exchange.getRequestURI().getQuery());
                    System.out.println("Path: "+exchange.getRequestURI().getPath());


                    System.out.println(params);
                    
                    // Separting Value from Key
                    String key = "Anonymous";
                    String name = params.getOrDefault("name", List.of(key)).stream().findFirst().orElse(key);
                    
                    // Formatting Response Output
                    String responseText = "Hi, "+ name;
                    
                    // Send Response 
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);
                    
                    OutputStream output = exchange.getResponseBody();
                    output.write(responseText.getBytes());
                    output.flush();
                }
                else {
                    // 405 not Allowed
                    exchange.sendResponseHeaders(405, -1);
                }
            }
        }));
    }

    public static void send3rdAPIRequest(HttpServer httpServer) {
        System.out.println("3rd API Request Started.");

         // 1st API to get Just message 
        httpServer.createContext(Main.APIURL + "getEmployeeSalary", (exchange -> {
            
            try (exchange) {
                if("GET".equals(exchange.getRequestMethod())) {
                    String param = splitPathToGetParam("getEmployeeSalary", exchange.getRequestURI().getPath());
                    String path = exchange.getRequestURI().getPath();

                    System.out.println("path: "+path);
                    System.out.println("param: "+param);

                    
                    String responseText = "Success getting Employee No =>'"+param+"' to check for its Salary";

                    // Success Message
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);

                    OutputStream output = exchange.getResponseBody();
                    output.write(responseText.getBytes());
                    output.flush();
                }
                else {
                    // Failed Message
                    exchange.sendResponseHeaders(405, -1);
                }
                exchange.close();
            }
        }));
    }
    
    public static void send4thAPIRequest(HttpServer httpServer) {
        System.out.println("4th API Request Started.");

         // 1st API to get Just message 
        httpServer.createContext(Main.APIURL + "employees", (exchange -> {
            
            try (exchange) {
                if("GET".equals(exchange.getRequestMethod())) {
                    
                    String responseText = "Success Getting Employees";

                    // Success Message
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);

                    OutputStream output = exchange.getResponseBody();
                    output.write(responseText.getBytes());
                    output.flush();
                }
                else {
                    // Failed Message
                    exchange.sendResponseHeaders(405, -1);
                }
            }
            exchange.close();
        }));
    }
    
    public static Map<String, List<String>> splitQuery(String query) {
        if(query == null || "".equals(query)) {
            return Collections.emptyMap();
        }
        return Pattern.compile("&").splitAsStream(query).
                map(s -> Arrays.copyOf(s.split("="), 2)).
                collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));
                
    }
    
    public static String decode(final String encoded) {
        try {
            return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
        }
        catch(final UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is a required Encodeing", e);
        }
    }
    
    public static String splitPathToGetParam(String routeName, String path) {
        if(path == null || "".equals(path)) {
            return String.format("No", "Param");
        }
        int subPath = path.lastIndexOf(routeName);
        System.out.println(subPath);
        return "Not stable in Getting Accurate Path";
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
                System.out.print(" | "+rsmd.getColumnName(i)+ " => " +columnValue);
            }
           System.out.println("");
        }
        System.out.println();
    } 
    
    // Formatted and Display ResultSet Selected Data   After Select Query Ran AND Brings ResultSet
    public static Map<String, String> FormatSelectedData(ResultSet rs) throws SQLException {
        
        ResultSetMetaData rsmd = rs.getMetaData();
        Map<String, String> formattedData = null;
        
        int columnsCount = rsmd.getColumnCount();
//        System.out.println();
        while(rs.next()) {
           for (int i = 1; i <= columnsCount; i++) {
//                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                formattedData.put(rsmd.getColumnName(i), columnValue);
//                System.out.print(" | "+rsmd.getColumnName(i)+ " => " +columnValue);
            }
//           System.out.println("");
        }
//        System.out.println();
        return formattedData;
    } 
}
