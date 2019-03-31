
package appen.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpenScene {   
    public Label nameText;
    public TextField nameField;
    public Label passwordText;
    public PasswordField passwordField;
    public Label failureText;
    public Button loginButton;
    public Button createAccountButton;
    
    public OpenScene() {
        nameText = new Label("Nickname: ");
        nameField = new TextField();
        passwordText = new Label("Password: ");
        passwordField = new PasswordField();
        failureText = new Label("");
        loginButton = new Button("Login");
        createAccountButton = new Button("Create an account");
    }
    
    public Scene getScene() {
        nameText = new Label("Nickname: ");
        nameField = new TextField();
        passwordText = new Label("Password: ");
        passwordField = new PasswordField();
        failureText = new Label("");
        loginButton = new Button("Login");
        createAccountButton = new Button("Create an account");
        
        VBox beg = new VBox();

        GridPane gridBeginning = new GridPane();
        gridBeginning.addRow(0, nameText, nameField);
        gridBeginning.addRow(1, passwordText, passwordField);

        gridBeginning.setHgap(10);
        gridBeginning.setVgap(10);

        HBox hBeg = new HBox();
        hBeg.getChildren().addAll(loginButton, createAccountButton);
        hBeg.setSpacing(10);

        beg.getChildren().addAll(gridBeginning,
                failureText, hBeg);
        beg.setPadding(new Insets(20, 20, 20, 20));
        beg.setSpacing(10);
        
        Scene scene = new Scene(beg, 400, 300);
        return scene;
    }
}
