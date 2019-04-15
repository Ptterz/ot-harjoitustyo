package appen.ui;

import appen.dao.*;
import appen.database.*;
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
    private int selectedLevel;

    @Override
    public void init() throws Exception {
        Database db = new Database("jdbc:sqlite:laradigappen.db");
        db.init();
        PlayerDao pd = new PlayerDao(db);
        ExerciseDao ed = new ExerciseDao(db);
        this.manage = new Management(pd, ed);
        this.tries = 0;
    }

    @Override
    public void start(Stage frame) throws Exception {

        window = frame;

        //---------------------------------------------------------------------
        //Aloitusnäkymä                                         Aloitusnäkymä
        //---------------------------------------------------------------------
        Label nameText = new Label("Nickname: ");
        TextField nameField = new TextField();
        Label passwordText = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        Label failureText = new Label("");
        Button loginButton = new Button("Login");
        Button createAccountButton = new Button("Create an account");

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

        Scene openScene = new Scene(beg, 400, 300);

        //---------------------------------------------------------------------
        //Tunnusten luonti                                    Tunnusten luonti
        //---------------------------------------------------------------------
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

        Scene createAccountScene = new Scene(vCreateAccount, 400, 300);

        //---------------------------------------------------------------------
        //Valikkonäkymä                                         Valikkonäkymä
        //---------------------------------------------------------------------
        Label menuText = new Label("Welcome!");
        Button playButton = new Button("Play");
        Button createButton = new Button("Create");
        Button scoreButton = new Button("Points");
        Button playerInfoButton = new Button("My account");
        Button logoutButton = new Button("Logout");
        Button quitButton = new Button("Quit");
        Label versionLabel = new Label("Version 1.0");

        VBox vMainMenu = new VBox();

        vMainMenu.getChildren().addAll(menuText, playButton, createButton, scoreButton,
                playerInfoButton, logoutButton, quitButton, versionLabel);
        vMainMenu.setPadding(new Insets(20, 20, 20, 20));
        vMainMenu.setSpacing(10);

        Scene mainMenuScene = new Scene(vMainMenu, 400, 300);

        //---------------------------------------------------------------------
        //Level - Game                                          Level - Game
        //---------------------------------------------------------------------
        Label choosePlayLevel = new Label("Choose a level.");
        Button playLevel1Button = new Button("Basics");
        Button playLevel2Button = new Button("Functions");
        Button playLevel3Button = new Button("Equations");
        Button playAllButton = new Button("Bring it on!");
        Label choosePlayLevelError = new Label("");

        VBox vChoosePlayLevel = new VBox();
        vChoosePlayLevel.getChildren().addAll(choosePlayLevel, playLevel1Button,
                playLevel2Button, playLevel3Button,
                playAllButton, choosePlayLevelError);
        vChoosePlayLevel.setPadding(new Insets(20, 20, 20, 20));
        vChoosePlayLevel.setSpacing(10);

        Scene choosePlayLevelScene = new Scene(vChoosePlayLevel, 400, 300);

        //---------------------------------------------------------------------
        //Game                                                      Game
        //---------------------------------------------------------------------
        Label exerciseText = new Label("");
        TextField answerField = new TextField();
        Label wrongAnswer = new Label("");
        Button checkAnswerButton = new Button("Submit your answer");
        Button forfeitButton = new Button("Forfeit");

        VBox vGame = new VBox();
        HBox hGame = new HBox();

        hGame.getChildren().addAll(checkAnswerButton, forfeitButton);
        hGame.setSpacing(10);

        vGame.getChildren().addAll(exerciseText, answerField, wrongAnswer, hGame);
        vGame.setPadding(new Insets(20, 20, 20, 20));
        vGame.setSpacing(10);

        Scene gameScene = new Scene(vGame, 400, 300);

        //---------------------------------------------------------------------
        //Answer                                                   Answer
        //---------------------------------------------------------------------
        Label headlineField = new Label("");
        Label triesField = new Label("Tries: ");
        Label timeSpent = new Label("Time spent: ");
        Button playAgainButton = new Button("Play again");
        Button mainMenuButton = new Button("Main menu");

        VBox vRightAnswer = new VBox();

        HBox hRightAnswer = new HBox();
        hRightAnswer.getChildren().addAll(playAgainButton, mainMenuButton);
        hRightAnswer.setSpacing(10);

        vRightAnswer.getChildren().addAll(headlineField, triesField, timeSpent, hRightAnswer);
        vRightAnswer.setPadding(new Insets(20, 20, 20, 20));
        vRightAnswer.setSpacing(10);

        Scene answerScene = new Scene(vRightAnswer, 400, 300);

        //---------------------------------------------------------------------
        //Level - Create                                        Level - Create
        //---------------------------------------------------------------------
        Label chooseCreteLevel = new Label("Choose a level.");
        Button createLevel1Button = new Button("Basics");
        Button createLevel2Button = new Button("Functions");
        Button createLevel3Button = new Button("Equations");
        Label chooseCreateLevelError = new Label("");

        VBox vChooseCreateLevel = new VBox();
        vChooseCreateLevel.getChildren().addAll(createLevel1Button,
                createLevel2Button, createLevel3Button, chooseCreateLevelError);
        vChooseCreateLevel.setPadding(new Insets(20, 20, 20, 20));
        vChooseCreateLevel.setSpacing(10);

        Scene chooseCreateLevelScene = new Scene(vChooseCreateLevel, 400, 300);

        //---------------------------------------------------------------------
        //Create 1                                                  Create 1
        //---------------------------------------------------------------------
        Label formulaText = new Label("The value of the formula should be 30 characters at most.\nFormula: ");
        TextField formulaField = new TextField();
        Label formulaErrorText = new Label("");
        Button submitFormulaButton = new Button("Submit");
        Button create1ReturnButton = new Button("Back");

        VBox vCreate1 = new VBox();

        HBox hCreate1 = new HBox();
        hCreate1.getChildren().addAll(submitFormulaButton, create1ReturnButton);
        hCreate1.setSpacing(10);

        vCreate1.getChildren().addAll(formulaText, formulaField, formulaErrorText, hCreate1);

        vCreate1.setPadding(new Insets(20, 20, 20, 20));
        vCreate1.setSpacing(10);

        Scene create1Scene = new Scene(vCreate1, 400, 300);

        //---------------------------------------------------------------------
        //Create 2                                                  Create 2
        //---------------------------------------------------------------------
        Label create2Text = new Label("The value of the function should be 30 characters at most.");
        Label functionText = new Label("f(x) =");
        Label functionValueText = new Label("x =");
        TextField functionField = new TextField();
        TextField functionValueField = new TextField();
        Label functionErrorText = new Label("");
        Button submitFunctionButton = new Button("Submit");
        Button create2ReturnButton = new Button("Back");

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

        Scene create2Scene = new Scene(vCreate2, 400, 300);

        //---------------------------------------------------------------------
        //Scoreboard                                            Scoreboard
        //---------------------------------------------------------------------
        Label scoreHeader = new Label("Scoreboard");
        Label scoreMessage = new Label("Scoreboard will be featured in upcoming versions.\nPlease, be patient.");
        Button scoreReturnButton = new Button("Back");

        VBox vScoreBoard = new VBox();

        vScoreBoard.getChildren().addAll(scoreHeader, scoreMessage, scoreReturnButton);

        vScoreBoard.setPadding(new Insets(20, 20, 20, 20));
        vScoreBoard.setSpacing(10);

        Scene scoreScene = new Scene(vScoreBoard, 400, 300);

        //---------------------------------------------------------------------
        //Player info                                           Player info
        //---------------------------------------------------------------------
        Label playerHeader = new Label("Player info");
        Button playerSetPasswordButton = new Button("Change password");
        Button playerReturnButton = new Button("Back");
        Label playerMessagePassword = new Label("");
        playerMessagePassword.setTextFill(Color.rgb(21, 117, 84));

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

        Scene playerInfoScene = new Scene(vPlayer, 400, 300);

        //---------------------------------------------------------------------
        //Logout                                                    Logout
        //---------------------------------------------------------------------
        BorderPane logoutBorder = new BorderPane();

        Label message = new Label("You have logged out. \nWindow will close in few seconds.");

        logoutBorder.setCenter(message);

        Scene logoutScene = new Scene(logoutBorder, 400, 300);

        //---------------------------------------------------------------------
        //Button functions                                    Button functions 
        //---------------------------------------------------------------------
        //openScene
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!manage.checkLoginEntry(nameField.getText(), passwordField.getText())) {
                    failureText.setText("Invalid nickname or password!");
                    failureText.setTextFill(Color.rgb(210, 39, 30));
                    return;
                }

                window.setScene(mainMenuScene);
            }
        });

        //openScene
        loginButton.setOnAction((event) -> {
            if (!manage.checkLoginEntry(nameField.getText(), passwordField.getText())) {
                failureText.setText("Invalid nickname or password!");
                failureText.setTextFill(Color.rgb(210, 39, 30));
                return;
            }

            window.setScene(mainMenuScene);
        });

        //openScene
        createAccountButton.setOnAction((event) -> {
            window.setScene(createAccountScene);
        });

        //createAccountScene
        checkNameAvailabilityButton.setOnAction((event) -> {

            String name = tryNameField.getText();

            if (!manage.checkEntryNickname(name)) {
                nameAvailable.setText("Invalid nickname!");
                nameAvailable.setTextFill(Color.rgb(210, 39, 30));
            } else if (!manage.checkNameAvailability(name)) {
                nameAvailable.setText("Nickname already taken!");
                nameAvailable.setTextFill(Color.rgb(210, 39, 30));
            } else {
                nameAvailable.setText("Nickname available!");
                nameAvailable.setTextFill(Color.rgb(21, 117, 84));
            }
        });

        //createAccountScene
        tryNameField.setOnKeyTyped((event) -> {
            nameAvailable.setText("");
        });

        //createAccountScene
        answerField.setOnKeyTyped((event) -> {
            wrongAnswer.setText("");
        });

        //createAccountScene
        tryPasswordField.setOnKeyTyped((event) -> {
            passwordNotMatch.setText("");
        });

        //createAccountScene
        tryPasswordField2.setOnKeyTyped((event) -> {
            passwordNotMatch.setText("");
        });

        //createAccountScene
        createNewAccountButton.setOnAction((event) -> {

            String ps1 = tryPasswordField.getText();
            String ps2 = tryPasswordField2.getText();

            if (!(manage.checkPasswordEntry(ps1, ps2))) {
                passwordNotMatch.setText("Invalid password!");
                passwordNotMatch.setTextFill(Color.rgb(210, 39, 30));
            } else {
                String name = tryNameField.getText();
                if (nameAvailable.getText().equals("Nickname available!")) {
                    if (manage.createAccount(name, ps1)) {
                        failureText.setText("");
                        nameField.clear();
                        passwordField.clear();
                        window.setScene(openScene);
                    } else {
                        passwordNotMatch.setText("Something went wrong. Try again.");
                    }
                } else {
                    passwordNotMatch.setText("Check if nickname available!");
                    passwordNotMatch.setTextFill(Color.rgb(210, 39, 30));
                }
            }
        });

        //createAccountScene
        backToLoginButton.setOnAction((event) -> {
            failureText.setText("");
            nameField.clear();
            passwordField.clear();
            window.setScene(openScene);
        });

        //mainMenuScene
        playButton.setOnAction((event) -> {
            window.setScene(choosePlayLevelScene);
        });

        //choosePlayLevelScene
        playLevel1Button.setOnAction((event) -> {
            choosePlayLevelError.setText("");
            manage.setSelectedPlayLevel(1);
            String solve = "Solve: " + manage.getExercise();
            exerciseText.setText(solve);
            window.setScene(gameScene);
        });

        //choosePlayLevelScene
        playLevel2Button.setOnAction((event) -> {
            choosePlayLevelError.setText("");
            manage.setSelectedPlayLevel(2);
            String solve = "Solve: " + manage.getExercise();
            exerciseText.setText(solve);
            window.setScene(gameScene);
        });

        //choosePlayLevelScene
        playLevel3Button.setOnAction((event) -> {
            choosePlayLevelError.setText("The option is not yet supported.");
            choosePlayLevelError.setTextFill(Color.rgb(210, 39, 30));
        });

        //choosePlayLevelScene
        playAllButton.setOnAction((event) -> {
            choosePlayLevelError.setText("");
            manage.setSelectedPlayLevel(0);
            String solve = "Solve: " + manage.getExercise();
            exerciseText.setText(solve);
            window.setScene(gameScene);
        });

        //mainMenuScene
        createButton.setOnAction((event) -> {
            window.setScene(chooseCreateLevelScene);
        });

        //chooseCreateLevelScene
        createLevel1Button.setOnAction((event) -> {
            chooseCreateLevelError.setText("");
            manage.setSelectedCreateLevel(1);
            window.setScene(create1Scene);
        });

        //chooseCreateLevelScene
        createLevel2Button.setOnAction((event) -> {
            chooseCreateLevelError.setText("");
            manage.setSelectedCreateLevel(2);
            window.setScene(create2Scene);
        });

        //chooseCreateLevelScene
        createLevel3Button.setOnAction((event) -> {
            chooseCreateLevelError.setText("The option is not yet supported.");
            chooseCreateLevelError.setTextFill(Color.rgb(210, 39, 30));
        });

        //gameScene
        checkAnswerButton.setOnAction((event) -> {
            String answer = manage.getAnswer();
            if (answerField.getText().equals(answer)) {
                String newExe = "Solve: " + manage.getExercise();
                exerciseText.setText(newExe);
                answerField.clear();
                headlineField.setText("Correct answer!");
                wrongAnswer.setText("");
                triesField.setText("Tries: " + tries);
                tries = 0;
                window.setScene(answerScene);
            } else {
                tries++;
                wrongAnswer.setText("Answer is not correct.");
                wrongAnswer.setTextFill(Color.rgb(210, 39, 30));
            }
        });

        //gameScene
        answerField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String answer = manage.getAnswer();
                if (answerField.getText().equals(answer)) {
                    String newExe = "Solve: " + manage.getExercise();
                    exerciseText.setText(newExe);
                    answerField.clear();
                    headlineField.setText("Correct answer!");
                    wrongAnswer.setText("");
                    triesField.setText("Tries: " + tries);
                    tries = 0;
                    window.setScene(answerScene);
                } else {
                    tries++;
                    wrongAnswer.setText("Answer is not correct.");
                    wrongAnswer.setTextFill(Color.rgb(210, 39, 30));
                }
            }
        });

        //gameScene
        forfeitButton.setOnAction((event) -> {
            String newExe = "Solve: " + manage.getExercise();
            exerciseText.setText(newExe);
            answerField.clear();
            headlineField.setText("You should try harder!");
            wrongAnswer.setText("");
            triesField.setText("Tries: ");
            tries = 0;
            window.setScene(answerScene);
        });

        //rightAnswerScene
        playAgainButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                window.setScene(gameScene);
            }
        });

        //rightAnswerScene
        playAgainButton.setOnAction((event) -> {
            window.setScene(gameScene);
        });

        //rightAnswerScene
        mainMenuButton.setOnAction((event) -> {
            window.setScene(mainMenuScene);
        });

        //rightAnswerScene
        mainMenuButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                window.setScene(mainMenuScene);
            }
        });

        //create1Scene
        submitFormulaButton.setOnAction((event) -> {
            //lisää tehtävä talteen ja tarkasta se(?)
            String s = formulaField.getText();
            if (manage.checkSubmittedFormula(s)) {
                if (manage.calculate(s)) {
                    formulaErrorText.setText("Exercise succesfully submitted!");
                    formulaErrorText.setTextFill(Color.rgb(21, 117, 84));
                    formulaField.clear();
                } else {
                    formulaErrorText.setText("Invalid formula!");
                    formulaErrorText.setTextFill(Color.rgb(210, 39, 30));
                }
            } else {
                formulaErrorText.setText("Invalid formula!");
                formulaErrorText.setTextFill(Color.rgb(210, 39, 30));
            }
        });

        //create2Scene
        submitFunctionButton.setOnAction((event) -> {
            String f = functionField.getText();
            String v = functionValueField.getText();
            if (manage.checkSubmittedFunction(f, v)) {
                if (manage.calculateFunction(f, v)) {
                    functionErrorText.setText("Exercise succesfully submitted!");
                    functionErrorText.setTextFill(Color.rgb(21, 117, 84));
                    functionField.clear();
                    functionValueField.clear();
                } else {
                    functionErrorText.setText("Invalid function and/or value!");
                    functionErrorText.setTextFill(Color.rgb(210, 39, 30));
                }
            } else {
                functionErrorText.setText("Invalid function and/or value!");
                functionErrorText.setTextFill(Color.rgb(210, 39, 30));
            }
        });

        //createScene
        create1ReturnButton.setOnAction((event) -> {
            formulaField.clear();
            window.setScene(mainMenuScene);
        });

        //createScene
        create2ReturnButton.setOnAction((event) -> {
            functionField.clear();
            functionValueField.clear();
            window.setScene(mainMenuScene);
        });

        //mainMenuScene
        scoreButton.setOnAction((event) -> {
            window.setScene(scoreScene);
        });

        //scoreScene
        scoreReturnButton.setOnAction((event) -> {
            window.setScene(mainMenuScene);
        });

        //mainMenuScene
        playerInfoButton.setOnAction((event) -> {
            playerHeader.setText(manage.getPlayerNick());
            window.setScene(playerInfoScene);
        });

        //playerInfoScene
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

        //playerInfoScene
        submitNewPasswordButton.setOnAction((event) -> {
            if (manage.getPlayerPassword().equals(oldPasswordField.getText())) {
                if (manage.checkPasswordEntry(newPasswordField1.getText(), newPasswordField2.getText())) {
                    if (manage.changePassword(newPasswordField1.getText())) {
                        oldPassword.setVisible(false);
                        newPassword1.setVisible(false);
                        newPassword2.setVisible(false);
                        oldPasswordField.setVisible(false);
                        newPasswordField1.setVisible(false);
                        newPasswordField2.setVisible(false);
                        submitNewPasswordButton.setVisible(false);
                        oldPasswordField.clear();
                        newPasswordField1.clear();
                        newPasswordField2.clear();
                        playerMessagePassword.setText("Password changed!");
                        playerMessagePassword.setTextFill(Color.rgb(21, 117, 84));
                    } else {
                        playerMessagePassword.setText("Something went wrong. Please try again.");
                        playerMessagePassword.setTextFill(Color.rgb(210, 39, 30));
                    }
                } else {
                    playerMessagePassword.setText("Passwords don't match!");
                    playerMessagePassword.setTextFill(Color.rgb(210, 39, 30));
                }
            } else {
                playerMessagePassword.setText("Invalid password!");
                playerMessagePassword.setTextFill(Color.rgb(210, 39, 30));
            }
        });

        //playerInfoScene
        playerReturnButton.setOnAction((event) -> {
            oldPassword.setVisible(false);
            newPassword1.setVisible(false);
            newPassword2.setVisible(false);
            oldPasswordField.setVisible(false);
            newPasswordField1.setVisible(false);
            newPasswordField2.setVisible(false);
            submitNewPasswordButton.setVisible(false);
            oldPasswordField.clear();
            newPasswordField1.clear();
            newPasswordField2.clear();
            playerMessagePassword.setText("");
            window.setScene(mainMenuScene);
        });

        //mainMenuScene
        logoutButton.setOnAction((event) -> {
            nameField.clear();
            passwordField.clear();
            window.setScene(openScene);
        });

        //mainMenuScene
        AnimationTimer timer = new AnimationTimer() {
            long edellinen = 0;
            long counter = 0;

            @Override
            public void handle(long nykyhetki) {
                if (nykyhetki - edellinen < 1000000000) {
                    if (counter < 400) {
                        counter++;
                        return;
                    }
                    window.close();
                }

                this.edellinen = nykyhetki;
            }
        };
        quitButton.setOnAction((event) -> {
            window.setScene(logoutScene);
            timer.start();
        });

        //---------------------------------------------------------------------
        window.setScene(openScene);
        window.show();
    }
}
