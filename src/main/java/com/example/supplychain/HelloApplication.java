package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static Group root;
    public static DatabaseConnection connection;
    public static String emailId;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        connection=new DatabaseConnection();
        emailId="";
        root=new Group();
        Header header=new Header();
        ProductPage products=new ProductPage();
        ListView<HBox> productLists=products.showProducts();
        AnchorPane productPane =new AnchorPane();
        productPane.setLayoutX(100);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productLists);
        root.getChildren().addAll(header.root,productPane);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Supply chain");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                connection.con.close();
                System.out.println("EXIT");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}