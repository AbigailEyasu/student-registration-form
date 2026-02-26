package com.example.studentregistration;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class studentRegistrationForm extends Application {

    // Simple student storage
    class Student {
        String fullName;
        String id;
        String yer;


        Student(String fullName, String id,String yer) {
            this.fullName = fullName;
            this.id = id;
            this.yer = yer;
        }
    }

    // Only one student for now
    private Student registeredStudent = null;

    // simple alert message method
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    Stage window;
    Scene registrationScene, loginScene, lcompletionScene, rcompletionScene;

    public void start(Stage primaryStage) {

        window = primaryStage;

        // creating borderpane to each scenes

        // creating borderpane to loginscene
        BorderPane root3 = new BorderPane();
        root3.setStyle("-fx-background-color: #e1def1ff;");

        // creating borderpane to registerationscene
        BorderPane root2 = new BorderPane();
        root2.setStyle("-fx-background-color: #e1def1ff;");

        // creating borderpane to homescene
        BorderPane root1 = new BorderPane();
        root1.setStyle("-fx-background-color: #e1def1ff;");

        // main title
        Label title = new Label("STUDENT REGISTRATION FORM");
        title.setStyle("-fx-font-size: 29px; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");

        // footer labels
        Label foLabel1 = new Label("© 2025 / E.© 2018 Hawassa University  – Student Rgistration Form ");
        foLabel1.setStyle("-fx-font-size: 12px; -fx-text-fill: #484545ff;");
        foLabel1.setPadding(new Insets(20));

        Label foLabel2 = new Label("© 2025 / E.© 2018 Hawassa University  – Student Rgistration Form ");
        foLabel2.setStyle("-fx-font-size: 12px; -fx-text-fill: #484545ff;");
        foLabel2.setPadding(new Insets(20));

        Label foLabel3 = new Label("© 2025 / E.© 2018 Hawassa University  – Student Login Form ");
        foLabel3.setStyle("-fx-font-size: 12px; -fx-text-fill: #484545ff;");
        foLabel3.setPadding(new Insets(20));

        // setting footer to each root
        root1.setBottom(foLabel1);
        root2.setBottom(foLabel2);
        root3.setBottom(foLabel3);

        // top title banner
        HBox hbox = new HBox(10, title);
        hbox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(hbox, Pos.CENTER);
        BorderPane.setMargin(hbox, new Insets(15));

        root1.setTop(hbox);

        // home page text
        Text infotext = new Text(
                " \t\t\t\t 👋Welcometo the Student Registration System \n" +
                        "\t This form allows new students to register and create their student account.\n" +
                        "\t\t Please enter accurate information to complete your registration.\n" +
                        "\t\t If you already have an account, simply click “Login” to continue."
        );
        infotext.setStyle("-fx-font-size: 14px; -fx-font-style: italic; -fx-fill: #333333;");

        // register button
        Button registerButton = new Button("Register");
        registerButton.setAlignment(Pos.CENTER);
        registerButton.setStyle("-fx-background-color: #00BCD4; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 25px;");

        // hover effect
        registerButton.setOnMouseEntered(e ->
                registerButton.setStyle(
                        "-fx-background-color: #26dab3ff; " +
                                "-fx-font-weight: bold; " +
                                "-fx-text-fill: black; " +
                                "-fx-padding: 10px; " +
                                "-fx-background-radius: 10px;"
                ));

        registerButton.setOnMouseExited(e ->
                registerButton.setStyle(
                        "-fx-background-color: #00BCD4; " +
                                "-fx-font-weight: bold; " +
                                "-fx-text-fill: black; " +
                                "-fx-padding: 10px; " +
                                "-fx-background-radius: 25px;"
                ));

        // login button
        Button logiButton = new Button("Login");
        logiButton.setAlignment(Pos.CENTER);
        logiButton.setStyle("-fx-background-color: #00BCD4; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 25px;");

        // hover effect
        logiButton.setOnMouseEntered(e ->
                logiButton.setStyle(
                        "-fx-background-color: #26dab3ff; " +
                                "-fx-font-weight: bold; " +
                                "-fx-text-fill: black; " +
                                "-fx-padding: 10px; " +
                                "-fx-background-radius: 10px;"
                ));

        logiButton.setOnMouseExited(e ->
                logiButton.setStyle(
                        "-fx-background-color: #00BCD4; " +
                                "-fx-font-weight: bold; " +
                                "-fx-text-fill: black; " +
                                "-fx-padding: 10px; " +
                                "-fx-background-radius: 25px;"
                ));

        // organizing home layout
        VBox vbox = new VBox(20, infotext, registerButton, logiButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(300);
        vbox.setPadding(new Insets(20));

        registerButton.setMaxWidth(Double.MAX_VALUE);
        logiButton.setMaxWidth(Double.MAX_VALUE);

        root1.setCenter(vbox);

        // creating home scene
        Scene homeScene = new Scene(root1, 600, 600);

        // set registration page on click
        registerButton.setOnAction(e -> window.setScene(registrationScene));

        // registration page title
        Label regTitle = new Label("STUDENT REGISTRATION");
        regTitle.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");

        Text reginfo = new Text(
                "\t\t\t\t 👋Welcometo the Student Registration Page \n" +
                        "\t\t\t Enter the following information carefully and completely"
        );
        reginfo.setStyle("-fx-font-size: 14px; -fx-font-style: italic; -fx-fill: #333333;");

        HBox regTitleBox = new HBox(regTitle);
        regTitleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        regTitleBox.setPadding(new Insets(10));
        regTitleBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(regTitleBox, Pos.CENTER);
        BorderPane.setMargin(regTitleBox, new Insets(15));

        root2.setTop(regTitleBox);

        // name fields

        HBox nameBox = new HBox(20);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(10));

        VBox fnameBox = new VBox(5);
        Label fname = new Label("First-Name");
        fname.setStyle("-fx-font-weight: bold");
        TextField fnamField = new TextField();
        fnamField.setPromptText(" Enter your first name");
        fnamField.setPrefWidth(200);
        fnameBox.getChildren().addAll(fname, fnamField);

        VBox lnameBox = new VBox(5);
        Label lname = new Label("Last- Name");
        lname.setStyle("-fx-font-weight: bold");
        TextField lnamField = new TextField();
        lnamField.setPromptText("Enter your last name");
        lnamField.setPrefWidth(180);
        lnameBox.getChildren().addAll(lname, lnamField);

        nameBox.getChildren().addAll(fnameBox, lnameBox);


        // age and year section

        HBox ageBox = new HBox(20);
        ageBox.setAlignment(Pos.CENTER);
        ageBox.setPadding(new Insets(10));

        Label age = new Label("Age :");
        age.setStyle("-fx-font-weight: bold");

        TextField ageField = new TextField();
        ageField.setPromptText("Your age");
        ageField.setPrefWidth(60);

        Label yearLabel = new Label("Year :");
        yearLabel.setStyle("-fx-font-weight: bold");

        ComboBox<String> year = new ComboBox<>();
        year.getItems().addAll("I", "II", "III", "IV", "V", "VI", "VII");
        year.setValue("Select");
        year.setPrefWidth(80);

        ageBox.getChildren().addAll(age, ageField, yearLabel, year);


        // gender

        HBox sBox = new HBox(20);
        sBox.setAlignment(Pos.CENTER);
        sBox.setPadding(new Insets(10));

        Label sex = new Label("Sex : ");
        sex.setStyle("-fx-font-weight: bold");

        RadioButton male = new RadioButton("MALE");
        RadioButton female = new RadioButton("FEMALE");

        ToggleGroup tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);

        sBox.getChildren().addAll(sex, male, female);


        // email box

        HBox mailBox = new HBox(20);
        mailBox.setAlignment(Pos.CENTER);
        mailBox.setPadding(new Insets(10));

        VBox emailBox = new VBox(5);
        Label email = new Label("Email");
        email.setStyle("-fx-font-weight: bold");

        TextField eField = new TextField();
        eField.setPromptText("Cristiano_Ronaldo@gmail.com");
        eField.setPrefWidth(200);

        emailBox.getChildren().addAll(email, eField);
        mailBox.getChildren().add(emailBox);


        // phone number

        HBox phoneBox = new HBox(30);
        phoneBox.setAlignment(Pos.CENTER);
        phoneBox.setPadding(new Insets(10));

        VBox phoneVBox = new VBox(5);
        Label phonenum = new Label("Phone-Number");
        phonenum.setStyle("-fx-font-weight: bold");

        TextField phField = new TextField();
        phField.setPromptText("09/07 12345678");
        phField.setPrefWidth(200);

        phoneVBox.getChildren().addAll(phonenum, phField);
        phoneBox.getChildren().add(phoneVBox);


        // emergency contact

        HBox emBox = new HBox(30);
        emBox.setAlignment(Pos.CENTER);
        emBox.setPadding(new Insets(10));

        VBox emVBox = new VBox(5);
        Label emergencynum = new Label("Emergency-Contact");
        emergencynum.setStyle("-fx-font-weight: bold");

        TextField emergencyField = new TextField();
        emergencyField.setPromptText("09/07 12345678");
        emergencyField.setPrefWidth(200);

        emVBox.getChildren().addAll(emergencynum, emergencyField);
        emBox.getChildren().add(emVBox);


        // submit + back buttons

        Button regSubmiButton = new Button("Submit");
        Button backHomeButton = new Button("Back");

        HBox submitBox = new HBox(10, regSubmiButton, backHomeButton);
        submitBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitBox.setPadding(new Insets(10));

        // submit button action validation
        regSubmiButton.setOnAction(e -> {

            // simple validation for empty fields
            if (fnamField.getText().isEmpty() ||
                    lnamField.getText().isEmpty() ||
                    ageField.getText().isEmpty() ||
                    year.getValue().equals("Select") ||
                    tg.getSelectedToggle() == null ||
                    eField.getText().isEmpty() ||
                    phField.getText().isEmpty() ||
                    emergencyField.getText().isEmpty()
            ) {
                showAlert(Alert.AlertType.ERROR, "All fields must be filled!");
                return;
            }

            // age validation
            try {
                int ageValue = Integer.parseInt(ageField.getText());
                if (ageValue < 16 || ageValue > 99) {
                    ageField.setStyle("-fx-bockground-color: red");
                    showAlert(Alert.AlertType.ERROR, "Age must be between 16 and 99!");
                    return;
                }
            } catch (NumberFormatException ex) {
                ageField.setStyle("-fx-bockground-color: red");
                showAlert(Alert.AlertType.ERROR, "Age must be a valid number!");
                return;
            }



            // email validation
            if (!eField.getText().contains("@gmail.com")) {
                eField.setStyle("-fx-bockground-color: red");
                showAlert(Alert.AlertType.ERROR, "Enter a valid email address!");
                return;
            }

            String fullName = fnamField.getText() + " " + lnamField.getText();
            String id = "NaScR/" + (int)(Math.random() * 9000 + 1000) + "/25";
            String yer = year.getValue();
            registeredStudent = new Student(fullName, id,yer);

            showAlert(Alert.AlertType.INFORMATION, "Registration Completed!\nName: " + fullName + "\nID: " + id);




            // clearing the form
            fnamField.clear();
            lnamField.clear();
            ageField.clear();
            year.setValue("Select");
            tg.selectToggle(null);
            eField.clear();
            phField.clear();
            emergencyField.clear();


            BorderPane root4 = new BorderPane();
            root4.setStyle("-fx-background-color: #e1def1ff;");

            Label studinfo = new Label("STUDENT PROFILE");
            studinfo.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");

            HBox hbox4 = new HBox(10, studinfo);
            hbox4.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
            hbox4.setPadding(new Insets(20));
            hbox4.setSpacing(15);
            hbox4.setAlignment(Pos.CENTER);

            BorderPane.setAlignment(hbox4, Pos.CENTER);
            BorderPane.setMargin(hbox4, new Insets(15));


            root4.setTop(hbox4);

            Text avatar = new Text("\uD83D\uDC64");
            avatar.setStyle("-fx-font-size: 65px;-fx-background-color: #036D9A; -fx-background-radius: 7px;-fx-font-weight: bold; -fx-padding: 10px;");
            Label studProfile = new Label("Full_Name: " + fullName +
                    "\nIDNO: " + id +
                    "\nYear : " + yer);
            studProfile.setStyle("-fx-font-weight: bold");
            studProfile.setAlignment(Pos.TOP_CENTER);

            VBox studProfileBox = new VBox(10);
            studProfileBox.setAlignment(Pos.TOP_CENTER);
            studProfileBox.setMaxWidth(200);
            studProfile.setMaxWidth(Double.MAX_VALUE);


            studProfileBox.getChildren().addAll(avatar,studProfile);
            root4.setCenter(studProfileBox);

            VBox leftMenu = new VBox(10);
            leftMenu.setPadding(new Insets(10));
            leftMenu.setSpacing(20);
            leftMenu.setPrefSize(200,200);
            leftMenu.setStyle("-fx-background-color: #6eb5e4e4; -fx-background-radius: 5px;");

            leftMenu.setPrefWidth(180);
            leftMenu.setMinWidth(180);
            leftMenu.setMaxWidth(180);


            Button b2 = new Button("Courses");
            Button b3 = new Button("Results");
            Button b4 = new Button("Logout");

            b4.setOnAction(_ -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to logout?",
                        ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    window.setScene(homeScene);
                }
            });



            for (Button b : new Button[]{b2, b3, b4}) {
                b.setMaxWidth(Double.MAX_VALUE);
                b.setAlignment(Pos.CENTER);
            }




            leftMenu.getChildren().addAll(b2, b3, b4);
            root4.setLeft(new VBox(leftMenu));
            Label foLabel4 = new Label("© 2025 / E.© 2018 Hawassa University  – Student Portal Form ");
            foLabel4.setStyle("-fx-font-size: 12px; -fx-text-fill: #484545ff;");
            foLabel4.setPadding(new Insets(20));
            root4.setBottom(foLabel4);


            lcompletionScene = new Scene(root4,600,600);
            window.setScene(lcompletionScene);


        });

        // back to home button
        backHomeButton.setOnAction(e -> window.setScene(homeScene));

        // registration page layout
        VBox regVBox = new VBox(reginfo, nameBox, ageBox, sBox, mailBox, phoneBox, emBox, submitBox);
        regVBox.setAlignment(Pos.CENTER);
        regVBox.setMaxWidth(500);

        root2.setCenter(regVBox);
        root2.setBottom(foLabel2);

        registrationScene = new Scene(root2, 600, 600);

        // switching to login scene
        logiButton.setOnAction(e -> window.setScene(loginScene));

        // login page section

        Label logTitle = new Label("STUDENT REGISTRATION");
        logTitle.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");

        Text loginfo = new Text(
                "\t\t\t\t 👋Welcometo the Student Login Page \n" +
                        "\t\t\t Enter the following information carefully and completely"
        );
        loginfo.setStyle("-fx-font-size: 14px; -fx-font-style: italic; -fx-fill: #333333;");

        HBox logTitleBox = new HBox(logTitle);
        logTitleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        logTitleBox.setPadding(new Insets(10));
        logTitleBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(logTitleBox, Pos.CENTER);
        BorderPane.setMargin(logTitleBox, new Insets(15));

        root3.setTop(logTitleBox);

        // name fields (login)
        HBox lognameBox = new HBox(20);
        lognameBox.setAlignment(Pos.CENTER);
        lognameBox.setPadding(new Insets(10));

        VBox logfnameBox = new VBox(5);
        Label logfname = new Label("First-Name");
        logfname.setStyle("-fx-font-weight: bold");

        TextField logfnamField = new TextField();
        logfnamField.setPromptText(" Enter your first name");
        logfnamField.setPrefWidth(200);

        logfnameBox.getChildren().addAll(logfname, logfnamField);

        VBox loglnameBox = new VBox(5);
        Label loglname = new Label("Last- Name");
        loglname.setStyle("-fx-font-weight: bold");

        TextField loglnamField = new TextField();
        loglnamField.setPromptText("Enter your last name");
        loglnamField.setPrefWidth(200);

        loglnameBox.getChildren().addAll(loglname, loglnamField);

        lognameBox.getChildren().addAll(logfnameBox, loglnameBox);

        // ID field
        VBox idVBox = new VBox(5);
        Label logId = new Label("Identification(IDNO)");
        logId.setStyle("-fx-font-weight: bold");

        TextField logIdfField = new TextField();
        logIdfField.setPromptText("NaScR/1111/25");
        logIdfField.setPrefWidth(80);

        idVBox.getChildren().addAll(logId, logIdfField);

        // password
        VBox passwordVBox = new VBox(5);
        Label passworLabel = new Label("Password");
        passworLabel.setStyle("-fx-font-weight: bold");

        PasswordField logpassword = new PasswordField();
        logpassword.setPromptText("Enter password");
        logpassword.setPrefWidth(80);

        passwordVBox.getChildren().addAll(passworLabel, logpassword);

        // login + back buttons
        HBox logButtonBox = new HBox(20);
        logButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        logButtonBox.setPadding(new Insets(10));

        Button loggButton = new Button("Login");
        Button logBackhomeButton = new Button("Back");

        logButtonBox.getChildren().addAll(loggButton, logBackhomeButton);


        loggButton.setOnAction(e -> {

            if (logfnamField.getText().isEmpty() ||
                    loglnamField.getText().isEmpty() ||
                    logIdfField.getText().isEmpty() ||
                    logpassword.getText().isEmpty()
            ) {
                showAlert(Alert.AlertType.ERROR, "All fields must be filled!");
                return;
            }
            if (registeredStudent == null) {
                showAlert(Alert.AlertType.ERROR, "No student registered yet!");
                return;
            }


            String fullNameInput = logfnamField.getText() + " " + loglnamField.getText();

            if (!fullNameInput.equals(registeredStudent.fullName)) {
                showAlert(Alert.AlertType.ERROR, "Name does not match registered student!");
                return;
            }


            showAlert(Alert.AlertType.INFORMATION, "Login Successful!\nName: " +
                    registeredStudent.fullName + "\nID: " + registeredStudent.id);



            // clear login fields
            logfnamField.clear();
            loglnamField.clear();
            logIdfField.clear();
            logpassword.clear();
             window.setScene(lcompletionScene);

        });

        // back to home
        logBackhomeButton.setOnAction(e -> window.setScene(homeScene));

        // login page layout
        VBox loginVBox = new VBox(20);
        loginVBox.setAlignment(Pos.CENTER);
        loginVBox.setMaxWidth(500);

        loginVBox.getChildren().addAll(loginfo, lognameBox, idVBox, passwordVBox, logButtonBox);

        root3.setCenter(loginVBox);

        loginScene = new Scene(root3, 600, 600);

        // setting home page initially
        window.setTitle("Student Registration Form");
        window.setScene(homeScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

