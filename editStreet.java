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

public class editStreet {
	private static TextField nameInput;
	private static TextField footInput;
	private static TextField filterInput;
	private static TableView<streetData> table;
	private static ObservableList<streetData> list = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display() {
		Stage window = new Stage();
		window.setTitle("Edit Streets");
		table = new TableView<>(); 
		
		TableColumn<streetData, String> nameCol = new TableColumn<>("Street Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<streetData, String> footCol = new TableColumn<>("Foot Traffic");
		footCol.setMinWidth(100);
		footCol.setCellValueFactory(new PropertyValueFactory<>("traffic"));
		
		table.setEditable(true);
		nameCol.setOnEditCommit(e -> nameEdit(e));
		footCol.setOnEditCommit(e -> footEdit(e));
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		footCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		// Text fields for add, edit, delete
		nameInput = new TextField();
		nameInput.setPromptText("Street Name");
		nameInput.setMinWidth(100);
		
		footInput = new TextField();
		footInput.setPromptText("Foot Traffic");
		
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
		hbox.getChildren().addAll(nameInput, footInput, filterInput, addButton, delButton, backButton);
		
		list = getStreetData();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(nameCol, footCol);
		
		VBox box = new VBox();
		box.getChildren().addAll(table, hbox);
		
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	
	// connect to a database
	public static ObservableList<streetData> getStreetData() {
		ObservableList<streetData> data = FXCollections.observableArrayList();
		data.add(new streetData("Sunset Blvd", "9000", "1000000"));
		data.add(new streetData("Washington Blvd", "8000", "500000"));
		data.add(new streetData("Jefferson Blvd", "6500", "400000"));
		data.add(new streetData("May Ave", "500", "5000"));
		data.add(new streetData("Bel Air", "100", "9999999"));
		
		return data;
	}
	
	public static void addClick() {
		streetData data = new streetData();
		data.setName(nameInput.getText());
		data.setTraffic(footInput.getText());
		table.getItems().add(data);
		nameInput.clear();
		footInput.clear();
	}
	
	public static void delClick() {
		ObservableList<streetData> selected, all;
		all = table.getItems();
		selected = table.getSelectionModel().getSelectedItems();
		
		selected.forEach(all::remove);
	}
	
	@SuppressWarnings("unchecked")
	public static void nameEdit(Event e) {
		TableColumn.CellEditEvent<streetData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<streetData, String>) e;
		streetData s = editEvent.getRowValue();
		s.setName(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void footEdit(Event e) {
		TableColumn.CellEditEvent<streetData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<streetData, String>) e;
		streetData s = editEvent.getRowValue();
		s.setTraffic(editEvent.getNewValue());
	}
	
	public static void filterList(String oldVal, String newVal) {
		ObservableList<streetData> filtered = FXCollections.observableArrayList();
		if(filterInput == null || (newVal.length() < oldVal.length()) || newVal == null)
			table.setItems(list);
		else {
			newVal = newVal.toUpperCase();
			for(streetData s : table.getItems()) {
				String name = s.getName();
				String traffic = s.getTraffic();
				if(name.toUpperCase().contains(newVal) || traffic.toUpperCase().contains(newVal))
					filtered.add(s);
			}
			table.setItems(filtered);
		}
	}

}
