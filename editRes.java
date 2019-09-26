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

public class editRes {
	private static TextField resInput;
	private static TextField streetInput;
	private static TextField deliveryInput;
	private static TextField revInput;
	private static TextField costInput;
	private static TextField hourInput;
	private static TextField filterInput;
	private static TableView<resData> table;
	private static ObservableList<resData> list = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display() {
		Stage window = new Stage();
		window.setTitle("Edit Restaurants");
		table = new TableView<>(); 
		
		TableColumn<resData, String> resCol = new TableColumn<>("Restaurant Name");
		resCol.setMinWidth(200);
		resCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<resData, String> streetCol = new TableColumn<>("Street Name");
		streetCol.setMinWidth(100);
		streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
		
		TableColumn<resData, String> deliveryCol = new TableColumn<>("Delivery");
		deliveryCol.setMinWidth(100);
		deliveryCol.setCellValueFactory(new PropertyValueFactory<>("delivery"));
		
		TableColumn<resData, String> revCol = new TableColumn<>("# of Reviews");
		revCol.setMinWidth(100);
		revCol.setCellValueFactory(new PropertyValueFactory<>("reviews"));
		
		TableColumn<resData, String> costCol = new TableColumn<>("Cost");
		costCol.setMinWidth(100);
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		TableColumn<resData, String> hourCol = new TableColumn<>("Hours");
		hourCol.setMinWidth(100);
		hourCol.setCellValueFactory(new PropertyValueFactory<>("hours"));
		
		table.setEditable(true);
		resCol.setOnEditCommit(e -> resEdit(e));
		streetCol.setOnEditCommit(e -> streetEdit(e));
		deliveryCol.setOnEditCommit(e -> deliveryEdit(e));
		revCol.setOnEditCommit(e -> revEdit(e));
		costCol.setOnEditCommit(e -> costEdit(e));
		hourCol.setOnEditCommit(e -> hourEdit(e));
		
		//table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		resCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
		deliveryCol.setCellFactory(TextFieldTableCell.forTableColumn());
		revCol.setCellFactory(TextFieldTableCell.forTableColumn());
		costCol.setCellFactory(TextFieldTableCell.forTableColumn());
		hourCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		// Text fields for add, edit, delete
		resInput = new TextField();
		resInput.setPromptText("Restaurant Name");
		
		streetInput = new TextField();
		streetInput.setPromptText("Street Name");
		
		deliveryInput = new TextField();
		deliveryInput.setPromptText("Delivery");
		
		revInput = new TextField();
		revInput.setPromptText("# of reviews");
		
		costInput = new TextField();
		costInput.setPromptText("Cost");
		
		hourInput = new TextField();
		hourInput.setPromptText("Hours");
		
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
		hbox.getChildren().addAll(resInput, streetInput, deliveryInput, revInput);
		
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10, 10, 10, 10));
		hbox2.setSpacing(10);
		hbox2.getChildren().addAll(costInput, hourInput, filterInput, addButton, delButton, backButton);
		
		list = getresData();
		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(resCol, streetCol, deliveryCol, revCol, costCol, hourCol);
		
		VBox box = new VBox();
		box.getChildren().addAll(table, hbox, hbox2);
		
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	
	// connect to a database
	public static ObservableList<resData> getresData() {
		ObservableList<resData> data = FXCollections.observableArrayList();
		data.add(new resData("Gorden Ramsey", "Sunset Blvd", "No", "9000", "4000", "16:00 - 21:00"));
		data.add(new resData("Hell's Kitchen", "Sunset Blvd", "No", "9000", "4000", "16:00 - 21:00"));
		data.add(new resData("Seirra", "Sunset Blvd", "No", "9000", "4000", "16:00 - 21:00"));
		data.add(new resData("Aurora", "Sunset Blvd", "No", "9000", "4000", "16:00 - 21:00"));
		data.add(new resData("Golden Cuisin", "Sunset Blvd", "No", "9000", "4000", "16:00 - 21:00"));
		
		return data;
	}
	
	public static void addClick() {
		resData data = new resData();
		data.setName(resInput.getText());
		data.setStreet(streetInput.getText());
		data.setDelivery(deliveryInput.getText());
		data.setReviews(revInput.getText());
		data.setCost(costInput.getText());
		data.setHours(hourInput.getText());
		table.getItems().add(data);
		resInput.clear();
		streetInput.clear();
		deliveryInput.clear();
		revInput.clear();
		costInput.clear();
		hourInput.clear();
	}
	
	public static void delClick() {
		ObservableList<resData> selected, all;
		all = table.getItems();
		selected = table.getSelectionModel().getSelectedItems();
		
		selected.forEach(all::remove);
	}
	
	@SuppressWarnings("unchecked")
	public static void resEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setName(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void streetEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setStreet(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void deliveryEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setDelivery(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void revEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setReviews(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void costEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setCost(editEvent.getNewValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void hourEdit(Event e) {
		TableColumn.CellEditEvent<resData, String> editEvent;
		editEvent = (TableColumn.CellEditEvent<resData, String>) e;
		resData s = editEvent.getRowValue();
		s.setHours(editEvent.getNewValue());
	}
	
	public static void filterList(String oldVal, String newVal) {
		ObservableList<resData> filtered = FXCollections.observableArrayList();
		if(filterInput == null || (newVal.length() < oldVal.length()) || newVal == null)
			table.setItems(list);
		else {
			newVal = newVal.toUpperCase();
			for(resData s : table.getItems()) {
				String name = s.getName();
				String street = s.getStreet();
				String delivery = s.getDelivery();
				String cost = s.getCost();
				String hours = s.getHours();
				if(name.toUpperCase().contains(newVal) || street.toUpperCase().contains(newVal)
						|| delivery.toUpperCase().contains(newVal) || cost.toUpperCase().contains(newVal) ||
						hours.toUpperCase().contains(newVal))
					filtered.add(s);
			}
			table.setItems(filtered);
		}
	}

}