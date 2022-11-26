package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.supplychain.HelloApplication.emailId;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(productID) from orders");
        int orderID=0;
        if(res.next()){
            orderID=res.getInt("max(productID)")+1;

            String query=String.format("Insert into orders values(%s,%s,'%s')",orderID,productID,HelloApplication.emailId);
            int response=HelloApplication.connection.executeUpdate(query);
            if(response>0){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Order");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Order is placed!!");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
                System.out.println("Order is placed :) ");
            }
        }

        }
}
