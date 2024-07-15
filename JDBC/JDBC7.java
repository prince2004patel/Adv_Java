// Btach Processing with JDBC

package com.mycompany.jdbc;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class JDBC7 {

    public static void main(String[] args) throws ClassNotFoundException {
        
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username , password);
            System.out.println("Connection Established Successfully !!!");
            con.setAutoCommit(false);
            String query = "insert into employees(name,job_title,salary) values(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);
            while(true){
               
                System.out.println("Enter Your Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Your Job Title:");
                String job_title = scanner.nextLine();
                System.out.println("Enter Your Salary:");
                Double salary = scanner.nextDouble();
            
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, job_title);
                preparedStatement.setDouble(3, salary);
                scanner.nextLine();
                preparedStatement.addBatch();
             
                System.out.println("add more value Y/N:");
                String decision = scanner.nextLine();
                if(decision.toUpperCase().equals("N")){
                      break;
                }
           }
            
            int[] batchResult = preparedStatement.executeBatch();
            con.commit();
            System.out.println("batch execute successfully");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
//            Statement stmt = con.createStatement();
//id is also in table but that is auto increment and primary key
//            stmt.addBatch("insert into employees(name,job_title,salary) values('meet','Cloud Engineer',71500);");
//            stmt.addBatch("insert into employees(name,job_title,salary) values('maitra','Data scientist',73000);");
//            stmt.addBatch("insert into employees(name,job_title,salary) values('vipul','Cyber Security Analyst',74000);");
//            
//            int[] batchResult = stmt.executeBatch();
//            con.commit();
//            System.out.println("batch executed successfully");