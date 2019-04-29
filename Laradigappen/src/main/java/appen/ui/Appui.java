package appen.ui;

import appen.dao.Database;
import appen.dao.*;
import appen.domain.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Appui extends Application {

    private Stage window;
    private Management manage;
    private int tries;
    private long gameBegin;
    private long gameEnd;
    private final Color COLOR_WARNING = Color.rgb(210, 39, 30);
    private final Color COLOR_OK = Color.rgb(21, 117, 84);

    @Override
    public void start(Stage frame) throws Exception {
        this.manage = new Management(true);
        this.tries = 0;
        window = frame;

        window.setScene(getOpenScene(window));
        window.show();
    }

    private Scene getOpenScene(Stage window) {
        Label nameText = new Label("Nickname: ");
        TextField nameField = new TextField();
        Label passwordText = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        Label failureText = new Label("");
        Button loginButton = new Button("Login");
        Button createAccountButton = new Button("Create an account");

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!manage.checkLoginEntry(nameField.getText(), passwordField.getText())) {
                    failureText.setText("Invalid nickname or password!");
                    failureText.setTextFill(COLOR_WARNING);
                } else {
                    window.setScene(getMainMenuScene(window));
                }
            }
        });

        loginButton.setOnAction((event) -> {
            if (!manage.checkLoginEntry(nameField.getText(), passwordField.getText())) {
                failureText.setText("Invalid nickname or password!");
                failureText.setTextFill(COLOR_WARNING);
            } else {
                window.setScene(getMainMenuScene(window));
            }
        });

        createAccountButton.setOnAction((event) -> {
            window.setScene(getCreateAccountScene(window));
        });

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

        return new Scene(beg, 400, 300);
    }

    private Scene getMainMenuScene(Stage window) {
        Label menuText = new Label("Welcome!");
        Button playButton = new Button("Play");
        Button createButton = new Button("Create");
        Button playerInfoButton = new Button("My account");
        Button logoutButton = new Button("Logout");
        Button quitButton = new Button("Quit");
        Label versionLabel = new Label("Version 1.0");

        createButton.setOnAction((event) -> {
            window.setScene(getChooseCreateLevelScene(window));
        });

        playButton.setOnAction((event) -> {
            window.setScene(getChooseGameLevelScene(window));
        });

        playerInfoButton.setOnAction((event) -> {
            window.setScene(getPlayerInfoScene(window));
        });

        logoutButton.setOnAction((event) -> {
            window.setScene(getOpenScene(window));
        });

        quitButton.setOnAction((event) -> {
            window.setScene(getLogoutScene());
            manage.getTimer(window).start();
        });

        VBox vMainMenu = new VBox();

        vMainMenu.getChildren().addAll(menuText, playButton, createButton,
                playerInfoButton, logoutButton, quitButton, versionLabel);
        vMainMenu.setPadding(new Insets(20, 20, 20, 20));
        vMainMenu.setSpacing(10);

        return new Scene(vMainMenu, 400, 300);
    }

    private Scene getCreateAccountScene(Stage window) {
        Label tryNameText = new Label("Nickname (max. 20 letters and numbers):");
        TextField tryNameField = new TextField();
        Label tryPasswordText = new Label("Password (max. 20 letters and numbers):");
        Label tryPasswordText2 = new Label("Retype password: ");
        PasswordField tryPasswordField = new PasswordField();
        PasswordField tryPasswordField2 = new PasswordField();
        Label nameAvailable = new Label("");
        Button checkNameAvailabilityButton = new Button("Check availability");
        Button createNewAccountButton = new Button("Create");
        Button backToLoginButton = new Button("Back");
        Label passwordNotMatch = new Label("");

        checkNameAvailabilityButton.setOnAction((event) -> {
            String name = tryNameField.getText();
            if (!manage.checkEntryNickname(name)) {
                nameAvailable.setText("Invalid nickname!");
                nameAvailable.setTextFill(COLOR_WARNING);
            } else if (!manage.checkNameAvailability(name)) {
                nameAvailable.setText("Nickname already taken!");
                nameAvailable.setTextFill(COLOR_WARNING);
            } else {
                nameAvailable.setText("Nickname available!");
                nameAvailable.setTextFill(COLOR_OK);
            }
        });

        tryNameField.setOnKeyTyped((event) -> {
            nameAvailable.setText("");
        });

        tryPasswordField.setOnKeyTyped((event) -> {
            passwordNotMatch.setText("");
        });

        tryPasswordField2.setOnKeyTyped((event) -> {
            passwordNotMatch.setText("");
        });

        createNewAccountButton.setOnAction((event) -> {
            String ps1 = tryPasswordField.getText();
            String ps2 = tryPasswordField2.getText();

            if (!(manage.checkPasswordEntry(ps1, ps2))) {
                passwordNotMatch.setText("Invalid password!");
                passwordNotMatch.setTextFill(COLOR_WARNING);
            } else {
                String name = tryNameField.getText();
                if (nameAvailable.getText().equals("Nickname available!")) {
                    if (manage.createAccount(name, ps1)) {
                        window.setScene(getOpenScene(window));
                    } else {
                        passwordNotMatch.setText("Something went wrong. Try again.");
                    }
                } else {
                    passwordNotMatch.setText("Check if nickname available!");
                    passwordNotMatch.setTextFill(COLOR_WARNING);
                }
            }
        });

        backToLoginButton.setOnAction((event) -> {
            window.setScene(getOpenScene(window));
        });

        VBox vCreateAccount = new VBox();

        GridPane gridCreateAccount1 = new GridPane();
        gridCreateAccount1.addRow(0, tryNameText);
        gridCreateAccount1.addRow(1, tryNameField);

        gridCreateAccount1.setHgap(10);
        gridCreateAccount1.setVgap(10);

        GridPane gridCreateAccount2 = new GridPane();
        gridCreateAccount2.addRow(0, tryPasswordText);
        gridCreateAccount2.addRow(1, tryPasswordField);
        gridCreateAccount2.addRow(2, tryPasswordText2);
        gridCreateAccount2.addRow(3, tryPasswordField2);

        gridCreateAccount2.setHgap(10);
        gridCreateAccount2.setVgap(10);

        GridPane gridCreateAccount3 = new GridPane();
        gridCreateAccount3.addRow(0, checkNameAvailabilityButton, nameAvailable);

        gridCreateAccount3.setHgap(10);
        gridCreateAccount3.setVgap(10);

        GridPane gridCreateAccount4 = new GridPane();
        gridCreateAccount4.addRow(0, createNewAccountButton, backToLoginButton, passwordNotMatch);

        gridCreateAccount4.setHgap(10);
        gridCreateAccount4.setVgap(10);

        vCreateAccount.getChildren().addAll(gridCreateAccount1,
                gridCreateAccount3, gridCreateAccount2, gridCreateAccount4);

        vCreateAccount.setPadding(new Insets(20, 20, 20, 20));
        vCreateAccount.setSpacing(10);

        return new Scene(vCreateAccount, 400, 300);
    }

    private Scene getChooseGameLevelScene(Stage window) {
        Label choosePlayLevel = new Label("Choose a level.");
        Button playLevel1Button = new Button("Basics");
        Button playLevel2Button = new Button("Functions");
        Button playLevel3Button = new Button("Equations");
        Button playAllButton = new Button("Bring it on!");
        Label choosePlayLevelError = new Label("");

        playLevel1Button.setOnAction((event) -> {
            gameBegin = System.currentTimeMillis();
            manage.setSelectedPlayLevel(1);
            window.setScene(getGameScene(window));
        });

        playLevel2Button.setOnAction((event) -> {
            gameBegin = System.currentTimeMillis();
            manage.setSelectedPlayLevel(2);
            window.setScene(getGameScene(window));
        });

        playLevel3Button.setOnAction((event) -> {
            gameBegin = System.currentTimeMillis();
            choosePlayLevelError.setText("The option is not yet supported.");
            choosePlayLevelError.setTextFill(COLOR_WARNING);
        });

        playAllButton.setOnAction((event) -> {
            gameBegin = System.currentTimeMillis();
            manage.setSelectedPlayLevel(0);
            window.setScene(getGameScene(window));
        });

        VBox vChoosePlayLevel = new VBox();
        vChoosePlayLevel.getChildren().addAll(choosePlayLevel, playLevel1Button,
                playLevel2Button, playLevel3Button,
                playAllButton, choosePlayLevelError);
        vChoosePlayLevel.setPadding(new Insets(20, 20, 20, 20));
        vChoosePlayLevel.setSpacing(10);

        return new Scene(vChoosePlayLevel, 400, 300);
    }

    private Scene getGameScene(Stage window) {
        String solve = "Solve: " + manage.getExercise();
        Label exerciseText = new Label(solve);
        TextField answerField = new TextField();
        Label wrongAnswer = new Label("");
        Button checkAnswerButton = new Button("Submit your answer");
        Button forfeitButton = new Button("Forfeit");

        checkAnswerButton.setOnAction((event) -> {
            String answer = manage.getAnswer();
            if (answerField.getText().equals(answer)) {
                gameEnd = System.currentTimeMillis();
                window.setScene(getAnswerScene(window, true));
            } else {
                tries++;
                wrongAnswer.setText("Answer is not correct.");
                wrongAnswer.setTextFill(COLOR_WARNING);
            }
        });

        answerField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String answer = manage.getAnswer();
                if (answerField.getText().equals(answer)) {
                    gameEnd = System.currentTimeMillis();
                    window.setScene(getAnswerScene(window, true));
                } else {
                    tries++;
                    wrongAnswer.setText("Answer is not correct.");
                    wrongAnswer.setTextFill(COLOR_WARNING);
                }
            }
        });

        forfeitButton.setOnAction((event) -> {
            window.setScene(getAnswerScene(window, false));
        });

        answerField.setOnKeyTyped((event) -> {
            wrongAnswer.setText("");
        });

        VBox vGame = new VBox();
        HBox hGame = new HBox();

        hGame.getChildren().addAll(checkAnswerButton, forfeitButton);
        hGame.setSpacing(10);

        vGame.getChildren().addAll(exerciseText, answerField, wrongAnswer, hGame);
        vGame.setPadding(new Insets(20, 20, 20, 20));
        vGame.setSpacing(10);

        return new Scene(vGame, 400, 300);
    }

    private Scene getAnswerScene(Stage window, boolean b) {
        Label headlineField = new Label("Results");
        Label triesField = new Label("");
        Label timeSpent = new Label("");
        Label statsRightAnswer = new Label("");
        Button playAgainButton = new Button("Play again");
        Button mainMenuButton = new Button("Main menu");

        if (b) {
            timeSpent.setText(manage.timeSpent(gameBegin, gameEnd));
            triesField.setText("Tries: " + tries);
            manage.createNewPerformance(tries, (gameEnd - gameBegin));
            statsRightAnswer.setText(manage.getResult());
        } else {
            triesField.setText("Tries: ");
            timeSpent.setText("Time spent: ");
            tries = 0;
        }

        playAgainButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gameBegin = System.currentTimeMillis();
                window.setScene(getGameScene(window));
            }
        });

        playAgainButton.setOnAction((event) -> {
            gameBegin = System.currentTimeMillis();
            window.setScene(getGameScene(window));
        });

        mainMenuButton.setOnAction((event) -> {
            window.setScene(getMainMenuScene(window));
        });

        mainMenuButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                window.setScene(getMainMenuScene(window));
            }
        });

        VBox vRightAnswer = new VBox();

        HBox hRightAnswer = new HBox();
        hRightAnswer.getChildren().addAll(playAgainButton, mainMenuButton);
        hRightAnswer.setSpacing(10);

        vRightAnswer.getChildren().addAll(headlineField, triesField, timeSpent,
                statsRightAnswer, hRightAnswer);
        vRightAnswer.setPadding(new Insets(20, 20, 20, 20));
        vRightAnswer.setSpacing(10);

        return new Scene(vRightAnswer, 400, 300);
    }

    private Scene getChooseCreateLevelScene(Stage window) {
        Label chooseCreateLevel = new Label("Choose a level.");
        Button createLevel1Button = new Button("Basics");
        Button createLevel2Button = new Button("Functions");
        Button createLevel3Button = new Button("Equations");
        Label chooseCreateLevelError = new Label("");

        createLevel1Button.setOnAction((event) -> {
            manage.setSelectedCreateLevel(1);
            window.setScene(getCreate1Scene(window));
        });

        createLevel2Button.setOnAction((event) -> {
            manage.setSelectedCreateLevel(2);
            window.setScene(getCreate2Scene(window));
        });

        createLevel3Button.setOnAction((event) -> {
            chooseCreateLevelError.setText("The option is not yet supported.");
            chooseCreateLevelError.setTextFill(COLOR_WARNING);
        });

        VBox vChooseCreateLevel = new VBox();
        vChooseCreateLevel.getChildren().addAll(chooseCreateLevel, createLevel1Button,
                createLevel2Button, createLevel3Button, chooseCreateLevelError);
        vChooseCreateLevel.setPadding(new Insets(20, 20, 20, 20));
        vChooseCreateLevel.setSpacing(10);

        return new Scene(vChooseCreateLevel, 400, 300);
    }

    private Scene getCreate1Scene(Stage window) {
        Label formulaText = new Label("Formula: ");
        TextField formulaField = new TextField();
        Label formulaErrorText = new Label("");
        Button submitFormulaButton = new Button("Submit");
        Button create1ReturnButton = new Button("Back");

        submitFormulaButton.setOnAction((event) -> {
            String s = formulaField.getText();
            if (manage.checkSubmittedFormula(s)) {
                if (manage.calculate(s)) {
                    formulaErrorText.setText("Exercise succesfully submitted!");
                    formulaErrorText.setTextFill(COLOR_OK);
                    formulaField.clear();
                } else {
                    formulaErrorText.setText("Invalid formula!");
                    formulaErrorText.setTextFill(COLOR_WARNING);
                }
            } else {
                formulaErrorText.setText("Invalid formula!");
                formulaErrorText.setTextFill(COLOR_WARNING);
            }
        });

        create1ReturnButton.setOnAction((event) -> {
            formulaField.clear();
            window.setScene(getMainMenuScene(window));
        });

        VBox vCreate1 = new VBox();

        HBox hCreate1 = new HBox();
        hCreate1.getChildren().addAll(submitFormulaButton, create1ReturnButton);
        hCreate1.setSpacing(10);

        vCreate1.getChildren().addAll(formulaText, formulaField, formulaErrorText, hCreate1);
        vCreate1.setPadding(new Insets(20, 20, 20, 20));
        vCreate1.setSpacing(10);

        return new Scene(vCreate1, 400, 300);
    }

    private Scene getCreate2Scene(Stage window) {
        Label create2Text = new Label("Enter both a function and a value for variable x.");
        Label functionText = new Label("f(x) =");
        Label functionValueText = new Label("x =");
        TextField functionField = new TextField();
        TextField functionValueField = new TextField();
        Label functionErrorText = new Label("");
        Button submitFunctionButton = new Button("Submit");
        Button create2ReturnButton = new Button("Back");

        submitFunctionButton.setOnAction((event) -> {
            String f = functionField.getText();
            String v = functionValueField.getText();
            if (manage.checkSubmittedFunction(f, v)) {
                if (manage.calculateFunction(f, v)) {
                    functionErrorText.setText("Exercise succesfully submitted!");
                    functionErrorText.setTextFill(COLOR_OK);
                } else {
                    functionErrorText.setText("Invalid function and/or value!");
                    functionErrorText.setTextFill(COLOR_WARNING);
                }
            } else {
                functionErrorText.setText("Invalid function and/or value!");
                functionErrorText.setTextFill(COLOR_WARNING);
            }
        });

        create2ReturnButton.setOnAction((event) -> {
            window.setScene(getMainMenuScene(window));
        });

        VBox vCreate2 = new VBox();

        GridPane gridCreate2 = new GridPane();
        gridCreate2.addRow(0, functionText, functionField);
        gridCreate2.addRow(1, functionValueText, functionValueField);
        gridCreate2.setHgap(10);
        gridCreate2.setVgap(10);

        HBox hCreate2 = new HBox();
        hCreate2.getChildren().addAll(submitFunctionButton, create2ReturnButton);
        hCreate2.setSpacing(10);

        vCreate2.getChildren().addAll(create2Text, gridCreate2, functionErrorText, hCreate2);
        vCreate2.setPadding(new Insets(20, 20, 20, 20));
        vCreate2.setSpacing(10);

        return new Scene(vCreate2, 400, 300);
    }

    private Scene getPlayerInfoScene(Stage window) {
        Label playerHeader = new Label(manage.getPlayerNick());
        Button playerSetPasswordButton = new Button("Change password");
        Button playerReturnButton = new Button("Back");
        Label playerMessagePassword = new Label("");

        Label oldPassword = new Label("Old password: ");
        oldPassword.setVisible(false);
        Label newPassword1 = new Label("New password: ");
        newPassword1.setVisible(false);
        Label newPassword2 = new Label("Retype password: ");
        newPassword2.setVisible(false);
        PasswordField oldPasswordField = new PasswordField();
        oldPasswordField.setVisible(false);
        PasswordField newPasswordField1 = new PasswordField();
        newPasswordField1.setVisible(false);
        PasswordField newPasswordField2 = new PasswordField();
        newPasswordField2.setVisible(false);
        Button submitNewPasswordButton = new Button("Submit changes");
        submitNewPasswordButton.setVisible(false);

        playerReturnButton.setOnAction((event) -> {
            window.setScene(getMainMenuScene(window));
        });

        submitNewPasswordButton.setOnAction((event) -> {
            if (manage.getPlayerPassword().equals(oldPasswordField.getText())) {
                if (manage.checkPasswordEntry(newPasswordField1.getText(), newPasswordField2.getText())) {
                    if (manage.changePassword(newPasswordField1.getText())) {
                        playerMessagePassword.setText("Password changed!");
                        playerMessagePassword.setTextFill(COLOR_OK);
                    } else {
                        playerMessagePassword.setText("Something went wrong. Please try again.");
                        playerMessagePassword.setTextFill(COLOR_WARNING);
                    }
                } else {
                    playerMessagePassword.setText("Passwords don't match!");
                    playerMessagePassword.setTextFill(COLOR_WARNING);
                }
            } else {
                playerMessagePassword.setText("Invalid password!");
                playerMessagePassword.setTextFill(COLOR_WARNING);
            }
        });

        playerSetPasswordButton.setOnAction((event) -> {
            playerMessagePassword.setText("");
            oldPassword.setVisible(true);
            newPassword1.setVisible(true);
            newPassword2.setVisible(true);
            oldPasswordField.setVisible(true);
            newPasswordField1.setVisible(true);
            newPasswordField2.setVisible(true);
            submitNewPasswordButton.setVisible(true);
        });

        VBox vPlayer = new VBox();
        HBox h1Player = new HBox();
        GridPane gPlayer = new GridPane();

        h1Player.getChildren().addAll(playerSetPasswordButton, playerReturnButton);
        h1Player.setSpacing(10);

        gPlayer.addColumn(0, oldPassword, newPassword1, newPassword2);
        gPlayer.addColumn(1, oldPasswordField, newPasswordField1, newPasswordField2);
        gPlayer.setHgap(10);
        gPlayer.setVgap(10);

        vPlayer.getChildren().addAll(playerHeader, h1Player, playerMessagePassword,
                gPlayer, submitNewPasswordButton);
        vPlayer.setPadding(new Insets(20, 20, 20, 20));
        vPlayer.setSpacing(10);

        return new Scene(vPlayer, 400, 300);
    }

    private Scene getLogoutScene() {
        BorderPane logoutBorder = new BorderPane();
        logoutBorder.setCenter(new Label("You have logged out. \nWindow closes in few seconds."));
        return new Scene(logoutBorder, 400, 300);
    }
}
