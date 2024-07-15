// java database Connectivity setup

package com.mycompany.jdbc;
import java.sql.*;

public class JDBC {

    public static void main(String[] args) throws ClassNotFoundException {
        
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        String query = "Select * from employees;";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username , password);
            System.out.println("Connection Established Successfully !!!");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
             
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String job_title = rs.getString("job_title");
                double salary = rs.getDouble("salary");
                
                System.out.println();
                System.out.println("============");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Job Title: " + job_title);
                System.out.println("Salary: " + salary);
               }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("-------------");
            System.out.println("Connection closed successfully !!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
