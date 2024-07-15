
// updating the data using jdbc into sql

package com.mycompany.jdbc;
import java.sql.*;

public class JDBC3 {

    public static void main(String[] args) throws ClassNotFoundException {
        
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        String query = "update employees set job_title = 'backend developer' , salary=76500 where id = 2;";
        
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
            int rowsafftected = stmt.executeUpdate(query);
            
            if(rowsafftected>0){
                System.out.println("update Successfull. " + rowsafftected+ " row(s) afftected");
            }else{
                System.out.println("update failed!!!");
            }
            
            
            stmt.close();
            con.close();
            System.out.println("-------------");
            System.out.println("Connection closed successfully !!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
