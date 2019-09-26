package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class addStreet {
	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(300);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label streetName = new Label("Street Name:");
		GridPane.setConstraints(streetName, 0, 0);
		TextField streetInput = new TextField();
		GridPane.setConstraints(streetInput, 1, 0);
		
		Label foot = new Label("Foot Traffic:");
		GridPane.setConstraints(foot, 0, 1);
		TextField footInput = new TextField();
		GridPane.setConstraints(footInput, 1, 1);
		
		Label rent = new Label("Rent:");
		GridPane.setConstraints(rent, 0, 2);
		TextField rentInput = new TextField();
		GridPane.setConstraints(rentInput, 1, 2);
		
		Button current = new Button("Edit Current Streets");
		current.setOnAction(e -> editStreet.display());
		GridPane.setConstraints(current, 1, 3);
		
		Button add = new Button("Add");
		add.setMinWidth(60);
		GridPane.setConstraints(add, 0, 5);
		
		Button close = new Button("Quit");
		close.setOnAction(e -> window.close());
		close.setMinWidth(60);
		GridPane.setConstraints(close, 1, 5);
		
		grid.getChildren().addAll(streetName, streetInput, foot, footInput, rent, rentInput, current, add, close);
		
		Scene scene = new Scene(grid, 350, 250);
		window.setScene(scene);
		window.showAndWait();
	}
}
