package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent event) throws SQLException,IOException {
       String query=String.format("Select * from user where emailId='%s' and pass='%s'",email.getText(),password.getText());
               ResultSet res=HelloApplication.connection.executeQuery(query);
               if(res.next()){
                   String userType=res.getString("userType");
                   HelloApplication.emailId=res.getString("emailId");
                   if(userType.equals("buyer")){
                       System.out.println("Buyer logged in");
                       Header header=new Header();
                       ProductPage products=new ProductPage();
                       ListView<HBox> productList=products.showProducts();

                       AnchorPane productPane =new AnchorPane();
                       productPane.setLayoutX(100);
                       productPane.setLayoutY(100);
                       productPane.getChildren().add(productList);
                       HelloApplication.root.getChildren().clear();
                       HelloApplication.root.getChildren().addAll(header.root,productPane);

                   }
                        else{
                       System.out.println("Seller logged in");
                       AnchorPane sellerPage= FXMLLoader.load((getClass().getResource("SellerPage.fxml")));
                       HelloApplication.root.getChildren().add(sellerPage);
                   }
               }
               else {
                   Dialog<String> dialog = new Dialog<>();
                   dialog.setTitle("Login");
                   ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                   dialog.setContentText("Login failed !!!...Try Again");
                   dialog.getDialogPane().getButtonTypes().add(type);
                   dialog.showAndWait();
               }
    }

}
