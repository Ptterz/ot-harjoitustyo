package appen.ui;

import appen.dao.*;
import appen.database.*;
import appen.domain.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        PlayerDao fpd = new PlayerDao(db);
        ExerciseDao fed = new ExerciseDao(db);
        this.manage = new Management(fpd, fed);
        manage.calculate("12*(4+6)");
        manage.calculate("12*(150-25+75)");
        manage.calculate("12*20");
        manage.calculate("12*22+15");
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
        Label tryPasswordText = new Label("Password (max. 20 letters and numbers): ");
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

        VBox vMainMenu = new VBox();

        HBox h1Main = new HBox();
        HBox h2Main = new HBox();

        h1Main.getChildren().addAll(playButton, createButton, scoreButton);
        h1Main.setSpacing(10);

        h2Main.getChildren().addAll(playerInfoButton, logoutButton, quitButton);
        h2Main.setSpacing(10);

        vMainMenu.getChildren().addAll(menuText, h1Main, h2Main);
        vMainMenu.setPadding(new Insets(20, 20, 20, 20));
        vMainMenu.setSpacing(10);

        Scene mainMenuScene = new Scene(vMainMenu, 400, 300);
        
        //---------------------------------------------------------------------
        //Tasovalinta                                           Tasovalinta
        //---------------------------------------------------------------------
        
        Label chooseLevel = new Label("Choose a level.");
        Button level1Button = new Button("Basics");
        Button level2Button = new Button("Functions");
        Button level3Button = new Button("Equations");
        Button allButton = new Button("Bring it on!");
        
        VBox vChooseLevel = new VBox();
        vChooseLevel.getChildren().addAll(level1Button, level2Button, level3Button, allButton);
        vChooseLevel.setPadding(new Insets(20, 20, 20, 20));
        vChooseLevel.setSpacing(10);
        
        Scene chooseLevelScene = new Scene(vChooseLevel, 400, 300);

        //---------------------------------------------------------------------
        //Pelinäkymä                                            Pelinäkymä
        //---------------------------------------------------------------------
        
        Label exerciseText = new Label("");
        TextField answerField = new TextField();
        Label wrongAnswer = new Label("");
        Button checkAnswerButton = new Button("Submit your answer");

        VBox vGame = new VBox();

        vGame.getChildren().addAll(exerciseText, answerField, wrongAnswer, checkAnswerButton);
        vGame.setPadding(new Insets(20, 20, 20, 20));
        vGame.setSpacing(10);

        Scene gameScene = new Scene(vGame, 400, 300);

        //---------------------------------------------------------------------
        //Oikea vastaus                                        Oikea vastaus
        //---------------------------------------------------------------------
        Label headlineField = new Label("Correct answer!");
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

        Scene rightAnswerScene = new Scene(vRightAnswer, 400, 300);

        //---------------------------------------------------------------------
        //Tehtävien luonti                                   Tehtävien luonti
        //---------------------------------------------------------------------
        Label formulaText = new Label("The value of the formula should be max 30 characters: ");
        TextField formulaField = new TextField();
        Label formulaErrorText = new Label("");
        Button submitButton = new Button("Submit");
        Button createReturnButton = new Button("Back");

        VBox vCreate = new VBox();

        HBox hCreate = new HBox();
        hCreate.getChildren().addAll(submitButton, createReturnButton);
        hCreate.setSpacing(10);

        vCreate.getChildren().addAll(formulaText, formulaField, formulaErrorText, hCreate);

        vCreate.setPadding(new Insets(20, 20, 20, 20));
        vCreate.setSpacing(10);

        Scene createScene = new Scene(vCreate, 400, 300);

        //---------------------------------------------------------------------
        //Scoreboard                                            Scoreboard
        //---------------------------------------------------------------------
        Label scoreHeader = new Label("Scoreboard");
        Button scoreReturnButton = new Button("Back");

        VBox vScoreBoard = new VBox();

        vScoreBoard.getChildren().addAll(scoreHeader, scoreReturnButton);

        vScoreBoard.setPadding(new Insets(20, 20, 20, 20));
        vScoreBoard.setSpacing(10);

        Scene scoreScene = new Scene(vScoreBoard, 400, 300);

        //---------------------------------------------------------------------
        //Pelaajan tiedot                                      Pelaajan tiedot
        //---------------------------------------------------------------------
        Label playerHeader = new Label("Player info");
        Button playerReturnButton = new Button("Back");

        VBox vPlayer = new VBox();

        vPlayer.getChildren().addAll(playerHeader, playerReturnButton);

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
        //Painikkeet                                            Painikkeet
        //---------------------------------------------------------------------
        //openScene
        loginButton.setOnAction((event) -> {
            //tarkasta, että nimi ja salasana eivät ole tyhjiä tai epäkelpoisia
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
            window.setScene(chooseLevelScene);
        });
        
        //chooseLevelScene
        level1Button.setOnAction((event) -> {
            manage.setSelectedLevel(1);
            String solve = "Solve: " + manage.getExercise();
            exerciseText.setText(solve);
            window.setScene(gameScene);
        });
        
        //chooseLevelScene
        allButton.setOnAction((event) -> {
            manage.setSelectedLevel(0);
            String solve = "Solve: " + manage.getExercise();
            exerciseText.setText(solve);
            window.setScene(gameScene);
        });

        //mainMenuScene
        createButton.setOnAction((event) -> {
            window.setScene(createScene);
        });

        //gameScene
        checkAnswerButton.setOnAction((event) -> {
            String answer = manage.getAnswer();
            if (answerField.getText().equals(answer)) {
                String newExe = "Solve: " + manage.getExercise();
                exerciseText.setText(newExe);
                answerField.clear();
                wrongAnswer.setText("");
                triesField.setText("Tries: " + tries);
                tries = 0;
                window.setScene(rightAnswerScene);
            } else {
                tries++;
                wrongAnswer.setText("Answer is not correct.");
                wrongAnswer.setTextFill(Color.rgb(210, 39, 30));
            }
        });
        
        //gameScene
        tryNameField.setOnKeyTyped((event) -> {
            nameAvailable.setText("");
        });

        //rightAnswerScene
        playAgainButton.setOnAction((event) -> {
            window.setScene(gameScene);
        });

        //rightAnswerScene
        mainMenuButton.setOnAction((event) -> {
            window.setScene(mainMenuScene);
        });

        //createScene
        submitButton.setOnAction((event) -> {
            //lisää tehtävä talteen ja tarkasta se(?)
            String s = formulaField.getText();
            if (manage.checkSubmittedFormula(s)) {
                if (manage.calculate(s)) {
                    formulaErrorText.setText("Exercise succesfully submitted!");
                    formulaErrorText.setTextFill(Color.rgb(21, 117, 84));
                    formulaField.clear();
                } else {
                    formulaErrorText.setText("Something went wrong! Try again.");
                    formulaErrorText.setTextFill(Color.rgb(210, 39, 30));
                }
            } else {
                formulaErrorText.setText("Invalid formula!");
                formulaErrorText.setTextFill(Color.rgb(210, 39, 30));
            }
        });

        //createScene
        createReturnButton.setOnAction((event) -> {
            formulaField.clear();
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
            window.setScene(playerInfoScene);
        });

        //playerInfoScene
        playerReturnButton.setOnAction((event) -> {
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
