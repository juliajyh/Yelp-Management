package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.*;

public class editDish {
	private static TextField dishInput;
	private static TextField resInput;
	private static TextField priceInput;
	private static TextField starInput;
	private static TextField filterInput;
	private static TableView<dishData> table;
	private static ObservableList<dishData> list = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display() {
		Stage window = new Stage();
		window.setTitle("Edit Dishes");
		table = new TableView<>(); 
		
		TableColumn<dishData, String> dishCol = new TableColumn<>("Dish Name");
		dishCol.setMinWidth(200);
		dishCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<dishData, String> resCol = new TableColumn<>("Restaurant Name");
		resCol.setMinWidth(100);
		resCol.setCellValueFactory(new PropertyValueFactory<>("res_name"));
		
		TableColumn<dishData, String> priceCol = new TableColumn<>("Price");
		priceCol.setMinWidth(100);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<dishData, String> starCol = new TableColumn<>("Star");
		starCol.setMinWidth(100);
		starCol.setCellValueFactory(new PropertyValueFactory<>("star"));
		
		
		table.setEditable(true);
		dishCol.setOnEditCommit(e -> dishEdit(e));
		resCol.setOnEditCommit(e -> resEdit(e));
		priceCol.setOnEditCommit(e -> priceEdit(e));
		starCol.setOnEditCommit(e -> starEdit(e));
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		dishCol.setCellFactory(TextFieldTableCell.forTableColumn());
		resCol.setCellFactory(TextFieldTableCell.forTableColumn());
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		starCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		// Text fields for add, edit, delete
		dishInput = new TextField();
		dishInput.setPromptText("Dish Name");
		
		resInput = new TextField();
		resInput.setPromptText("Restaurant Name");
		
		priceInput = new TextField();
		priceInput.setPromptText("Price");
		
		starInput = new TextField();
		starInput.setPromptText("Star");
		
		filterInput = new TextField();
		filterInput.setPromptText("Search...");
		
		filterInput.textProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				filterList((String) oldValue, (String) newValue);
			}
		});
		
		// buttons for add, edit, delete
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addClick());
		
		Button delButton = new Button("Delete");
		delButton.setOnAction(e -> delClick());
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> window.close());
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(dishInput, resInput, priceInput, starInput);
		
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10, 10, 10, 10));
		hbox2.setSpacing(10);
		hbox2.getChildren().addAll(filterInput, addButton, delButton, backButton);
		
		list = getdishData();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(dishCol, resCol, priceCol, starCol);
		
		VBox box = new VBox();
		box.getChildren().addAll(table, hbox, hbox2);
		
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	
	// connect to a database
	public static ObservableList<dishData> getdishData() {
		ObservableList<dishData> data = FXCollections.observableArrayList();
		data.add(new dishData("HotDog", "Gorden Ramsey", "899.99", "5"));
		data.add(new dishData("Ripeye Steak", "Sunrise", "49.99", "6"));
		data.add(new dishData("Blood Mary", "The Bar", "19.99", "3"));
		data.add(new dishData("Fish&Chip", "Aurora", "17.99", "4"));
		data.add(new dishData("Egg Fried Rice", "Imperium", "9.99", "6"));
		
		return data;
	}
	
	public static void addClick() {
		dishData data = new dishData();
		data.setName(dishInput.getText());
		data.setRes_name(resInput.getText());
		data.setPrice(priceInput.getText());
		data.setStar(starInput.getText());
		table.getItems().add(data);
		resInput.clear();
		dishInput.clear();
		priceInput.clear();
		starInput.clear();
	}
	
	public static void delClick() {
		ObservableList<dishData> selected, all;
		all = table.getItems();
		selected = table.getSelectionModel().getSelectedItems();
		
		selected.forEach(all::remove);
	}
	
	@SuppressWarnings("unchecked")
	public static void resEdit(Event e) {
		TableColumn.CellEditEvent<dishData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<dishData, String>) e;
		dishData s = editEvent.getRowValue();
		s.setRes_name(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void dishEdit(Event e) {
		TableColumn.CellEditEvent<dishData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<dishData, String>) e;
		dishData s = editEvent.getRowValue();
		s.setName(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void priceEdit(Event e) {
		TableColumn.CellEditEvent<dishData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<dishData, String>) e;
		dishData s = editEvent.getRowValue();
		s.setPrice(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void starEdit(Event e) {
		TableColumn.CellEditEvent<dishData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<dishData, String>) e;
		dishData s = editEvent.getRowValue();
		s.setStar(editEvent.getNewValue());
	}
	
	public static void filterList(String oldVal, String newVal) {
		ObservableList<dishData> filtered = FXCollections.observableArrayList();
		if(filterInput == null || (newVal.length() < oldVal.length()) || newVal == null)
			table.setItems(list);
		else {
			newVal = newVal.toUpperCase();
			for(dishData s : table.getItems()) {
				String name = s.getName();
				String res = s.getRes_name();
				String price = s.getPrice();
				String star = s.getStar();
				
				if(name.toUpperCase().contains(newVal) || res.toUpperCase().contains(newVal)
						|| price.toUpperCase().contains(newVal) || star.toUpperCase().contains(newVal))
					filtered.add(s);
			}
			table.setItems(filtered);
		}
	}
}
