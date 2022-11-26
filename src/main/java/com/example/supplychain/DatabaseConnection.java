package com.example.supplychain;

import java.sql.*;

public class DatabaseConnection {
    String SQLURL="jdbc:mysql://localhost:3306/supply?useSSL=false";
    String username="root";
    String password="rootpassword";
    Connection con=null;
    DatabaseConnection(){
        try{
            con= DriverManager.getConnection(SQLURL,username,password);
            if(con!=null){
                System.out.println("DataBase connection is SUCCESS FUL");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


   public ResultSet executeQuery(String query)  {
        ResultSet res=null;
        try{
            Statement statement= con.createStatement();
            res=statement.executeQuery(query);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
       }
        return res;
   }
   public int executeUpdate(String query){
        int res=0;
        try{
            Statement statement= con.createStatement();
            res=statement.executeUpdate(query);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
   }
}
