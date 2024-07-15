// transaction handling in JDBC

package com.mycompany.jdbc;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class JDBC6 {

    public static void main(String[] args) throws ClassNotFoundException {
        
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        String withdrawQuery = "update accounts set balance = balance - ? where account_number=?;";
        String depositQuery = "update accounts set balance = balance + ? where account_number=?;";
        
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
            try{
               PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
               PreparedStatement depositStatement = con.prepareStatement(depositQuery);
               withdrawStatement.setDouble(1, 500.00);
               withdrawStatement.setString(2, "account456");
               depositStatement.setDouble(1, 500.00);
               depositStatement.setString(2, "account123");
               int rowsAffectedWithdrawl = withdrawStatement.executeUpdate();
               int rowsAffectedDeposit = depositStatement.executeUpdate();
               if(rowsAffectedWithdrawl > 0 && rowsAffectedDeposit>0){
                   con.commit();
                   System.out.println("Transaction Successfully");

               }else{
                   con.rollback();
                   System.out.println("Transcation Failed");
               }
              
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
