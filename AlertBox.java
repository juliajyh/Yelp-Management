package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	public static void display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(300);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Button search = new Button("Make Query");
		search.setOnAction(e -> query.display());
		search.setMinWidth(280);
		GridPane.setConstraints(search, 0, 1);
		
		Button street = new Button("Manage Streets");
		street.setMinWidth(280);
		street.setOnAction(e -> editStreet.display());
		GridPane.setConstraints(street, 0, 2);
		
		Button res = new Button("Manage Restaurants");
		res.setMinWidth(280);
		res.setOnAction(e -> editRes.display());
		GridPane.setConstraints(res, 0, 3);
		
		Button cus = new Button("Manage Customers");
		cus.setMinWidth(280);
		cus.setOnAction(e -> editCus.display());
		GridPane.setConstraints(cus, 0, 5);
		
		Button dish = new Button("Manage Dishes");
		dish.setMinWidth(280);
		dish.setOnAction(e -> editDish.display());
		GridPane.setConstraints(dish, 0, 4);
		
		
		Button closeButton = new Button("Logout");
		closeButton.setMinWidth(280);
		GridPane.setConstraints(closeButton, 0, 10);
		closeButton.setOnAction(e -> window.close());
		
		grid.getChildren().addAll(search, street, res, dish, cus, closeButton);
		Scene scene = new Scene(grid, 300, 350);
		
		window.setScene(scene);
		window.showAndWait();
	}
}
