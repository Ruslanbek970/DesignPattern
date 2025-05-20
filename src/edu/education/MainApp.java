package edu.education;

import edu.education.facade.TeachingFacade;
import edu.education.model.Professor;
import edu.education.model.Student;
import edu.education.processes.TeachingProcess;
import edu.education.util.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;


public class MainApp extends Application {
    private TeachingFacade facade;
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        Professor prof = new Professor();
        facade = new TeachingFacade(prof);
        facade.registerStudent("Alice");
        facade.registerStudent("Bob");
        facade.registerStudent("Carol");

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Dashboard Tab
        VBox dashboard = new VBox(20);
        dashboard.setPadding(new Insets(20));
        HBox controls = new HBox(20);
        controls.setPadding(new Insets(15));

        Button btnLecture = new Button("Schedule Lecture");
        Button btnAssignment = new Button("Schedule Homework");
        Button btnReview = new Button("Schedule Review");
        Button btnFullSession = new Button("Full Session");

        btnLecture.setPrefSize(100, 50);
        btnAssignment.setPrefSize(100, 50);
        btnReview.setPrefSize(100, 50);
        btnFullSession.setPrefSize(100, 50);

        controls.getChildren().addAll(btnLecture, btnAssignment, btnReview, btnFullSession);

        logArea = new TextArea();
        logArea.setPrefSize(1000, 600);
        logArea.setEditable(false);

        dashboard.getChildren().addAll(controls, new Label("Event Log:"), logArea);
        Tab tab1 = new Tab("Dashboard", dashboard);

        // Calendar Tab
        TableView<TeachingProcess> calendarTable = new TableView<>();
        calendarTable.setPrefSize(600, 600);

        TableColumn<TeachingProcess, String> descCol = new TableColumn<>("Session");
        descCol.setPrefWidth(400);
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<TeachingProcess, String> timeCol = new TableColumn<>("Scheduled Time");
        timeCol.setPrefWidth(400);
        timeCol.setCellValueFactory(cell -> {
            if (cell.getValue() == null) return new SimpleStringProperty("N/A");
            LocalDateTime time = cell.getValue().getScheduledTime();
            return new SimpleStringProperty(
                    time != null
                            ? time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            : "N/A"
            );
        });

        calendarTable.getColumns().addAll(descCol, timeCol);
        calendarTable.setItems(facade.getProcesses());

        ScrollPane calendarScroll = new ScrollPane(calendarTable);
        calendarScroll.setFitToWidth(true);
        Tab tab2 = new Tab("Calendar", calendarScroll);

        // Students Tab
        TableView<Student> studentTable = new TableView<>();
        studentTable.setPrefSize(1000, 600);

        TableColumn<Student, String> nameCol = new TableColumn<>("Student");
        nameCol.setPrefWidth(300);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Number> lectureCol = new TableColumn<>("Lectures");
        lectureCol.setPrefWidth(200);
        lectureCol.setCellValueFactory(new PropertyValueFactory<>("lectures"));

        TableColumn<Student, Number> homeworkCol = new TableColumn<>("Homeworks");
        homeworkCol.setPrefWidth(200);
        homeworkCol.setCellValueFactory(new PropertyValueFactory<>("homeworks"));

        TableColumn<Student, Number> reviewCol = new TableColumn<>("Reviews");
        reviewCol.setPrefWidth(200);
        reviewCol.setCellValueFactory(new PropertyValueFactory<>("reviews"));

        TableColumn<Student, Number> avgScoreCol = new TableColumn<>("Average Score");
        avgScoreCol.setPrefWidth(300);
        avgScoreCol.setCellValueFactory(new PropertyValueFactory<>("averageScore"));

        studentTable.getColumns().addAll(nameCol, lectureCol, homeworkCol, reviewCol, avgScoreCol);
        studentTable.setItems(FXCollections.observableArrayList(facade.getStudents()));

        ScrollPane studentScroll = new ScrollPane(studentTable);
        studentScroll.setFitToWidth(true);
        Tab tab3 = new Tab("Students", studentScroll);

        tabPane.getTabs().addAll(tab1, tab2, tab3);

        // Event Handlers
        btnLecture.setOnAction(e -> facade.processLecture(1000));
        btnAssignment.setOnAction(e -> facade.processAssignment(1500));
        btnReview.setOnAction(e -> facade.processReview(2000));
        btnFullSession.setOnAction(e -> facade.processFullSession(2500));

        Logger.getInstance().addListener(msg -> Platform.runLater(() ->
                logArea.appendText(msg + "\n")
        ));

        Scene scene = new Scene(tabPane, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Education Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}