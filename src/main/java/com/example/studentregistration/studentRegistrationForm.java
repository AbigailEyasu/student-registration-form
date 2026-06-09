package com.example.studentregistration;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;

/**
 * Student Registration Form v2.0 - Database-backed with Search & Delete
 */
public class studentRegistrationForm extends Application {

    private DatabaseManager dbManager;
    private StudentDAO studentDAO;
    Stage window;
    Scene homeScene, registrationScene, loginScene, viewStudentsScene;

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        dbManager = new DatabaseManager();
        dbManager.init();
        studentDAO = new StudentDAO(dbManager.getConnection());

        window = primaryStage;

        createHomeScene();
        createRegistrationScene();
        createLoginScene();
        createViewStudentsScene();

        window.setTitle("Student Registration System v2.0 - Database Backed");
        window.setScene(homeScene);
        window.setOnCloseRequest(e -> dbManager.close());
        window.show();
    }

    private void createHomeScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e1def1ff;");

        Label title = new Label("STUDENT REGISTRATION SYSTEM v2.0");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox titleBox = new HBox(title);
        titleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        titleBox.setPadding(new Insets(15));
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        Text infotext = new Text(
                "👋 Welcome to Student Registration System\n" +
                        "✅ Database-Backed Version with Persistent Storage\n\n" +
                        "Features:\n" +
                        "• Register new students\n" +
                        "• View & Search all students\n" +
                        "• Delete student records\n" +
                        "• Student login verification\n" +
                        "• Persistent SQLite database"
        );
        infotext.setStyle("-fx-font-size: 13px; -fx-fill: #333333;");

        Button registerBtn = createButton("Register New Student");
        Button viewStudentsBtn = createButton("View All Students");
        Button loginBtn = createButton("Student Login");

        registerBtn.setOnAction(e -> window.setScene(registrationScene));
        viewStudentsBtn.setOnAction(e -> window.setScene(viewStudentsScene));
        loginBtn.setOnAction(e -> window.setScene(loginScene));

        VBox vbox = new VBox(20, infotext, registerBtn, viewStudentsBtn, loginBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(450);
        vbox.setPadding(new Insets(30));

        registerBtn.setMaxWidth(Double.MAX_VALUE);
        viewStudentsBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        root.setCenter(vbox);

        Label footer = new Label("© 2025 Hawassa University – Student Registration Form v2.0");
        footer.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");
        footer.setPadding(new Insets(15));
        root.setBottom(footer);

        homeScene = new Scene(root, 750, 700);
    }

    private void createRegistrationScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e1def1ff;");

        Label regTitle = new Label("STUDENT REGISTRATION");
        regTitle.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox titleBox = new HBox(regTitle);
        titleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        TextField fnameField = new TextField();
        fnameField.setPromptText("First name");
        fnameField.setPrefWidth(200);

        TextField lnameField = new TextField();
        lnameField.setPromptText("Last name");
        lnameField.setPrefWidth(200);

        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setPrefWidth(100);

        ComboBox<String> yearBox = new ComboBox<>();
        yearBox.getItems().addAll("I", "II", "III", "IV", "V", "VI", "VII");
        yearBox.setValue("Select Year");

        RadioButton maleRb = new RadioButton("Male");
        RadioButton femaleRb = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleRb.setToggleGroup(genderGroup);
        femaleRb.setToggleGroup(genderGroup);

        TextField emailField = new TextField();
        emailField.setPromptText("student@email.com");
        emailField.setPrefWidth(200);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone number");
        phoneField.setPrefWidth(200);

        TextField emergencyField = new TextField();
        emergencyField.setPromptText("Emergency contact");
        emergencyField.setPrefWidth(200);

        Button submitBtn = createButton("Submit Registration");
        submitBtn.setOnAction(e -> {
            if (fnameField.getText().isEmpty() || lnameField.getText().isEmpty() ||
                    ageField.getText().isEmpty() || yearBox.getValue().equals("Select Year") ||
                    genderGroup.getSelectedToggle() == null || emailField.getText().isEmpty() ||
                    phoneField.getText().isEmpty() || emergencyField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "All fields are required!");
                return;
            }

            try {
                int age = Integer.parseInt(ageField.getText());
                if (age < 16 || age > 99) {
                    showAlert(Alert.AlertType.ERROR, "Age must be between 16 and 99!");
                    return;
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Age must be a number!");
                return;
            }

            if (!emailField.getText().contains("@")) {
                showAlert(Alert.AlertType.ERROR, "Invalid email address!");
                return;
            }

            String fullName = fnameField.getText() + " " + lnameField.getText();
            String studentId = "NaScR/" + (int)(Math.random() * 9000 + 1000) + "/25";
            String gender = maleRb.isSelected() ? "Male" : "Female";

            Student student = new Student(
                    fullName,
                    studentId,
                    yearBox.getValue(),
                    Integer.parseInt(ageField.getText()),
                    gender,
                    emailField.getText(),
                    phoneField.getText(),
                    emergencyField.getText()
            );

            if (studentDAO.addStudent(student)) {
                showAlert(Alert.AlertType.INFORMATION, 
                    "✅ Registration Successful!\n\nName: " + fullName + 
                    "\nStudent ID: " + studentId + 
                    "\nYear: " + yearBox.getValue());

                fnameField.clear();
                lnameField.clear();
                ageField.clear();
                yearBox.setValue("Select Year");
                genderGroup.selectToggle(null);
                emailField.clear();
                phoneField.clear();
                emergencyField.clear();

                window.setScene(homeScene);
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to register student!");
            }
        });

        Button backBtn = createButton("Back");
        backBtn.setOnAction(e -> window.setScene(homeScene));

        VBox formBox = new VBox(12);
        formBox.setPadding(new Insets(20));
        formBox.setMaxWidth(420);

        addFormField(formBox, "First Name", fnameField);
        addFormField(formBox, "Last Name", lnameField);
        addFormField(formBox, "Age", ageField);
        addFormField(formBox, "Year", yearBox);

        HBox genderBox = new HBox(20);
        genderBox.getChildren().addAll(new Label("Gender:"), maleRb, femaleRb);
        formBox.getChildren().add(genderBox);

        addFormField(formBox, "Email", emailField);
        addFormField(formBox, "Phone", phoneField);
        addFormField(formBox, "Emergency Contact", emergencyField);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(submitBtn, backBtn);
        formBox.getChildren().add(buttonBox);

        root.setCenter(formBox);

        registrationScene = new Scene(root, 750, 700);
    }

    private void createLoginScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e1def1ff;");

        Label loginTitle = new Label("STUDENT LOGIN");
        loginTitle.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox titleBox = new HBox(loginTitle);
        titleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your full name");
        nameField.setPrefWidth(250);

        TextField idField = new TextField();
        idField.setPromptText("NaScR/1111/25");
        idField.setPrefWidth(250);

        Button loginBtn = createButton("Login");
        loginBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || idField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "All fields required!");
                return;
            }

            Student student = studentDAO.getStudentByStudentId(idField.getText());
            if (student != null && student.getFullName().equalsIgnoreCase(nameField.getText())) {
                showAlert(Alert.AlertType.INFORMATION, 
                    "✅ Login Successful!\nWelcome " + student.getFullName());
                nameField.clear();
                idField.clear();
                window.setScene(homeScene);
            } else {
                showAlert(Alert.AlertType.ERROR, "❌ Invalid credentials!");
            }
        });

        Button backBtn = createButton("Back");
        backBtn.setOnAction(e -> window.setScene(homeScene));

        VBox formBox = new VBox(15);
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(300);
        formBox.setAlignment(Pos.CENTER);
        formBox.getChildren().addAll(
                new Label("Full Name:"), nameField,
                new Label("Student ID:"), idField,
                loginBtn, backBtn
        );

        root.setCenter(formBox);

        loginScene = new Scene(root, 750, 700);
    }

    private void createViewStudentsScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e1def1ff;");

        Label title = new Label("STUDENT MANAGEMENT");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox titleBox = new HBox(title);
        titleBox.setStyle("-fx-background-color: #036D9A; -fx-background-radius: 7px;");
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        // Search box
        HBox searchBox = new HBox(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-background-color: #f0f0f0;");
        
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name, ID, or email...");
        searchField.setPrefWidth(300);

        Button searchBtn = createButton("Search");

        Label countLabel = new Label("Total Students: 0");
        countLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        searchBox.getChildren().addAll(new Label("Search:"), searchField, searchBtn, new Separator(), countLabel);

        // Table
        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFullName()));

        TableColumn<Student, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudentId()));

        TableColumn<Student, String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getYear()));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));

        table.getColumns().addAll(nameCol, idCol, yearCol, emailCol);

        List<Student> students = studentDAO.getAllStudents();
        ObservableList<Student> data = FXCollections.observableArrayList(students);
        table.setItems(data);

        countLabel.setText("Total Students: " + studentDAO.getTotalStudentCount());

        searchBtn.setOnAction(e -> {
            String searchTerm = searchField.getText();
            if (searchTerm.isEmpty()) {
                data.setAll(studentDAO.getAllStudents());
            } else {
                data.setAll(studentDAO.searchStudents(searchTerm));
            }
            countLabel.setText("Total Students: " + data.size());
        });

        // Buttons
        Button refreshBtn = createButton("Refresh");
        refreshBtn.setOnAction(e -> {
            students.clear();
            students.addAll(studentDAO.getAllStudents());
            data.setAll(students);
            searchField.clear();
            countLabel.setText("Total Students: " + studentDAO.getTotalStudentCount());
        });

        Button deleteBtn = createButton("Delete Selected");
        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "Please select a student to delete!");
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Delete " + selected.getFullName() + "?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (studentDAO.deleteStudent(selected.getId())) {
                    showAlert(Alert.AlertType.INFORMATION, "✅ Student deleted successfully!");
                    data.remove(selected);
                    countLabel.setText("Total Students: " + studentDAO.getTotalStudentCount());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to delete student!");
                }
            }
        });

        Button backBtn = createButton("Back");
        backBtn.setOnAction(e -> window.setScene(homeScene));

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15));
        buttonBox.getChildren().addAll(refreshBtn, deleteBtn, backBtn);

        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10));
        centerBox.getChildren().addAll(searchBox, table);

        root.setCenter(centerBox);
        root.setBottom(buttonBox);

        viewStudentsScene = new Scene(root, 950, 700);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #00BCD4; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 25px;");
        
        button.setOnMouseEntered(e -> 
            button.setStyle("-fx-background-color: #26dab3ff; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 10px;"));
        
        button.setOnMouseExited(e -> 
            button.setStyle("-fx-background-color: #00BCD4; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 25px;"));
        
        return button;
    }

    private void addFormField(VBox container, String labelText, javafx.scene.Node field) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold");
        VBox fieldBox = new VBox(5);
        fieldBox.getChildren().addAll(label, field);
        container.getChildren().add(fieldBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
