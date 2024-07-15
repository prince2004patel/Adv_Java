// image handling with JDBC

package com.mycompany.jdbc;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class JDBC5 {
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException{
        
        String url= "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "P@1331patel";
        String folder_path = "D:\\prince\\";
        String query = "select image_data from image_table where image_id=(?);";
        
        //this is for inserting image
//        String image_path = "D:\\prince\\PRINCE (2).jpeg";
//        String query = "insert into image_table(image_data) values(?);";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("connection established successfully");
            
           PreparedStatement preparedStatement = con.prepareStatement(query);
           preparedStatement.setInt(1,1);
           ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path + "extractedImage.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
            }else{
                System.out.println("image not found!!!");
            }
            
            
            //this is for inserting image
//            FileInputStream fileInputStream = new FileInputStream(image_path);
//            byte[] imageData = new byte[fileInputStream.available()];
//            fileInputStream.read(imageData);
//            
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setBytes(1,imageData);
//            int affectedRows = preparedStatement.executeUpdate();
//            if(affectedRows>0){
//                System.out.println("image inserted successfully");
//            }else{
//                System.out.println("image not inserted");
//            }
//            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        
            
    }
    
}
