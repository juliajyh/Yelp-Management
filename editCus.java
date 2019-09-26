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

public class editCus {
	private static TextField nameInput;
	private static TextField picInput;
	private static TextField revInput;
	private static TextField filterInput;
	private static TableView<cusData> table;
	private static ObservableList<cusData> list = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display() {
		Stage window = new Stage();
		window.setTitle("Edit Dishes");
		table = new TableView<>(); 
		
		TableColumn<cusData, String> nameCol = new TableColumn<>("Username");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<cusData, String> picCol = new TableColumn<>("# of Pictures");
		picCol.setMinWidth(100);
		picCol.setCellValueFactory(new PropertyValueFactory<>("pictures"));
		
		TableColumn<cusData, String> revCol = new TableColumn<>("# of Reviews");
		revCol.setMinWidth(100);
		revCol.setCellValueFactory(new PropertyValueFactory<>("reviews"));
		
		
		table.setEditable(true);
		nameCol.setOnEditCommit(e -> nameEdit(e));
		picCol.setOnEditCommit(e -> picEdit(e));
		revCol.setOnEditCommit(e -> revEdit(e));
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		picCol.setCellFactory(TextFieldTableCell.forTableColumn());
		revCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		// Text fields for add, edit, delete
		nameInput = new TextField();
		nameInput.setPromptText("Username");
		
		picInput = new TextField();
		picInput.setPromptText("# of Pictures");
		
		revInput = new TextField();
		revInput.setPromptText("# of Reviews");
		
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
		hbox.getChildren().addAll(nameInput, picInput, revInput);
		
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10, 10, 10, 10));
		hbox2.setSpacing(10);
		hbox2.getChildren().addAll(filterInput, addButton, delButton, backButton);
		
		list = getcusData();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(nameCol, picCol, revCol);
		
		VBox box = new VBox();
		box.getChildren().addAll(table, hbox, hbox2);
		
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	
	// connect to a database
	public static ObservableList<cusData> getcusData() {
		ObservableList<cusData> data = FXCollections.observableArrayList();
		data.add(new cusData("star999", "999", "1023"));
		data.add(new cusData("Kate", "12", "3"));
		data.add(new cusData("keyboard_warrior", "7182", "2345"));
		data.add(new cusData("Fish&Chip", "33", "5"));
		data.add(new cusData("pikachu101", "233", "96"));
		
		return data;
	}
	
	public static void addClick() {
		cusData data = new cusData();
		data.setName(nameInput.getText());
		data.setPictures(picInput.getText());
		data.setReviews(revInput.getText());
		table.getItems().add(data);
		picInput.clear();
		nameInput.clear();
		revInput.clear();
	}
	
	public static void delClick() {
		ObservableList<cusData> selected, all;
		all = table.getItems();
		selected = table.getSelectionModel().getSelectedItems();
		
		selected.forEach(all::remove);
	}
	
	@SuppressWarnings("unchecked")
	public static void picEdit(Event e) {
		TableColumn.CellEditEvent<cusData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<cusData, String>) e;
		cusData s = editEvent.getRowValue();
		s.setPictures(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void nameEdit(Event e) {
		TableColumn.CellEditEvent<cusData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<cusData, String>) e;
		cusData s = editEvent.getRowValue();
		s.setName(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void revEdit(Event e) {
		TableColumn.CellEditEvent<cusData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<cusData, String>) e;
		cusData s = editEvent.getRowValue();
		s.setReviews(editEvent.getNewValue());
	}
	
	public static void filterList(String oldVal, String newVal) {
		ObservableList<cusData> filtered = FXCollections.observableArrayList();
		if(filterInput == null || (newVal.length() < oldVal.length()) || newVal == null)
			table.setItems(list);
		else {
			newVal = newVal.toUpperCase();
			for(cusData s : table.getItems()) {
				String name = s.getName();
				String res = s.getPictures();
				String price = s.getReviews();
				
				if(name.toUpperCase().contains(newVal) || res.toUpperCase().contains(newVal)
						|| price.toUpperCase().contains(newVal))
					filtered.add(s);
			}
			table.setItems(filtered);
		}
	}
}
