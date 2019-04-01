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

    @Override
    public void init() throws Exception {
        Database db = new Database("jdbc:sqlite:laradigappen.db");
        db.init();
        PlayerDao fpd = new PlayerDao(db);
        ExerciseDao fed = new ExerciseDao(db);
        this.manage = new Management(fpd, fed);
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
        //Pelinäkymä                                            Pelinäkymä
        //---------------------------------------------------------------------
        Label exerciseText = new Label("Tehtävä: ");
        Label exerciseField = new Label("(tähän tehtävä)");
        TextField answerField = new TextField();
        Label wrongAnswer = new Label("");
        Button checkAnswerButton = new Button("Tarkista");

        GridPane gridGame = new GridPane();
        gridGame.add(exerciseText, 0, 0);
        gridGame.add(exerciseField, 1, 0);
        gridGame.add(answerField, 1, 1);
        gridGame.add(wrongAnswer, 1, 2);
        gridGame.add(checkAnswerButton, 1, 3);

        gridGame.setHgap(10);
        gridGame.setVgap(10);
        gridGame.setPadding(new Insets(20, 20, 20, 20));

        Scene gameScene = new Scene(gridGame, 400, 300);

        //---------------------------------------------------------------------
        //Oikea vastaus                                        Oikea vastaus
        //---------------------------------------------------------------------
        Label headlineField = new Label("Vastaus oikein!");
        Label triesField = new Label("Yrityksiä: X");
        Label timeSpent = new Label("Aikaa kului: NaN sekuntia");
        Button playAgainButton = new Button("Pelaa uudelleen");
        Button mainMenuButton = new Button("Valikko");

        GridPane gridRightAnswer = new GridPane();
        gridRightAnswer.add(headlineField, 0, 0);
        gridRightAnswer.add(triesField, 0, 1);
        gridRightAnswer.add(timeSpent, 0, 2);
        gridRightAnswer.add(playAgainButton, 0, 3);
        gridRightAnswer.add(mainMenuButton, 1, 3);

        gridRightAnswer.setHgap(10);
        gridRightAnswer.setVgap(10);
        gridRightAnswer.setPadding(new Insets(20, 20, 20, 20));

        Scene rightAnswerScene = new Scene(gridRightAnswer, 400, 300);

        //---------------------------------------------------------------------
        //Tehtävien luonti                                   Tehtävien luonti
        //---------------------------------------------------------------------
        Label formulaText = new Label("Lauseke (enintään 50 merkkiä): ");
        TextField formulaField = new TextField();
        Label formulaErrorText = new Label("");
        Button submitButton = new Button("Lisää");
        Button createReturnButton = new Button("Palaa päävalikkoon");

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
        Label scoreHeader = new Label("Pistetilanne");
        Button scoreReturnButton = new Button("Palaa päävalikkoon");

        VBox vScoreBoard = new VBox();

        vScoreBoard.getChildren().addAll(scoreHeader, scoreReturnButton);

        vScoreBoard.setPadding(new Insets(20, 20, 20, 20));
        vScoreBoard.setSpacing(10);

        Scene scoreScene = new Scene(vScoreBoard, 400, 300);

        //---------------------------------------------------------------------
        //Pelaajan tiedot                                      Pelaajan tiedot
        //---------------------------------------------------------------------
        Label playerHeader = new Label("Omat tiedot");
        Button playerReturnButton = new Button("Palaa päävalikkoon");

        VBox vPlayer = new VBox();

        vPlayer.getChildren().addAll(playerHeader, playerReturnButton);

        vPlayer.setPadding(new Insets(20, 20, 20, 20));
        vPlayer.setSpacing(10);

        Scene playerInfoScene = new Scene(vPlayer, 400, 300);

        //---------------------------------------------------------------------
        //Logout                                                    Logout
        //---------------------------------------------------------------------
        BorderPane logoutBorder = new BorderPane();

        Label message = new Label("Olet kirjautunut ulos. \nIkkuna sulkeutuu automaattisesti.");

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

            String name = tryNameField.getText().trim();

            if (!(name.length() == 0 || name.equals(tryNameField.getText()) || name.length() > 20)) {
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
            window.setScene(gameScene);
        });

        //mainMenuScene
        createButton.setOnAction((event) -> {
            window.setScene(createScene);
        });

        //gameScene
        checkAnswerButton.setOnAction((event) -> {
            //Varmennus!
            window.setScene(rightAnswerScene);
        });

        //rightAnswerScene
        playAgainButton.setOnAction((event) -> {
            //Varmista, että kysymys vaihdettu ja vastauskenttä tyhjä
            window.setScene(gameScene);
        });

        //rightAnswerScene
        mainMenuButton.setOnAction((event) -> {
            window.setScene(mainMenuScene);
        });

        //createScene
        submitButton.setOnAction((event) -> {
            //lisää tehtävä talteen ja tarkasta se(?)
            formulaField.clear();
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
            manage.quit();
            window.setScene(logoutScene);
            timer.start();
        });

        //---------------------------------------------------------------------
        window.setScene(openScene);
        window.show();
    }
}
