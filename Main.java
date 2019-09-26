package application;
	
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Main extends Application {
	Stage window;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			window.setTitle("Not Yelp");
			
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);
			
			Label name = new Label("Username:");
			GridPane.setConstraints(name, 0, 0);
			TextField nameInput = new TextField();
			GridPane.setConstraints(nameInput, 1, 0);
			
			Label password = new Label("Password:");
			GridPane.setConstraints(password, 0, 1);
			TextField passInput = new TextField();
			passInput.setPromptText("Password");
			GridPane.setConstraints(passInput, 1, 1);
			
			Button login = new Button("Login");
			login.setOnAction(e -> AlertBox.display("Main Menu", "what do you want"));
			GridPane.setConstraints(login, 1, 2);
			
			grid.getChildren().addAll(name, nameInput, password, passInput, login);
			
			Scene scene = new Scene(grid, 300, 250);
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
