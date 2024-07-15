// prepared statement 

package com.mycompany.jdbc;
import javax.xml.transform.Result;
import java.util.Scanner;
import java.sql.*;

public class JDBC4{

    public static void main(String[] args) throws ClassNotFoundException {
        
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        String query = "insert into employees(id,name,job_title,salary) values(?,?,?,?);";
//        String query = "Select * from employees where name = ? AND job_title = ? ";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username , password);
            System.out.println("Connection Established Successfully !!!");
//            Statement stmt = con.createStatement();
         
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Enter Your ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Your Job Title:");
            String job_title = scanner.nextLine();
            System.out.println("Enter Your Salary:");
            Double salary = scanner.nextDouble();
            
            PreparedStatement preparedStatement = con.prepareStatement(query);
           
//            preparedStatement.setString(1,"prince");
//            preparedStatement.setString(2,"ML developer");

              preparedStatement.setInt(1, id);
              preparedStatement.setString(2, name);
              preparedStatement.setString(3, job_title);
              preparedStatement.setDouble(4, salary);

              int rowAffected = preparedStatement.executeUpdate();
              if(rowAffected>0){
                  System.out.println("data inserted successfully");
              }else{
                  System.out.println("data insertion failed");
              }
              
//            ResultSet resultSet = preparedStatement.executeQuery();
//            
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String job_title = resultSet.getString("job_title");
//                double salary = resultSet.getDouble("salary");
//                
//                System.out.println("id: "+id);
//                System.out.println("name: "+name);
//                System.out.println("job title: " + job_title);
//                System.out.println("salary: "+salary);
//                
//            }
            
//            resultSet.close();
            preparedStatement.close();
            con.close();
            System.out.println("-------------");
            System.out.println("Connection closed successfully !!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
