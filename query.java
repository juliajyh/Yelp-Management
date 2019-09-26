package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class query {
	private static TextField streetInput;
	private static TextField costInput;
	private static Button searchButton;
	
	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Make Query");
		window.setMinWidth(300);
		window.setMinHeight(300);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label name = new Label("Street:");
		GridPane.setConstraints(name, 0, 0);
		streetInput = new TextField();
		streetInput.setPromptText("Street Name");
		GridPane.setConstraints(streetInput, 1, 0);
		
		Label cost = new Label("Cost:");
		GridPane.setConstraints(cost, 0, 1);
		costInput = new TextField();
		costInput.setPromptText("Cost Name");
		GridPane.setConstraints(costInput, 1, 1);
		
		searchButton = new Button("Make Query");
		//searchButton.setMinWidth(280);
		GridPane.setConstraints(searchButton, 1, 2);
		
		
		grid.getChildren().addAll(name, streetInput, cost, costInput, searchButton);
		Scene scene = new Scene(grid, 300, 350);
		
		window.setScene(scene);
		window.showAndWait();
	}
}

