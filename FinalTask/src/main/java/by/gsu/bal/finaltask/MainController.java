package by.gsu.bal.finaltask;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class MainController {
  @FXML
  private TableColumn<PayRecord, Float> cWaterCostColumn;

  @FXML
  private TableColumn<PayRecord, Date> cWaterDateColumn;

  @FXML
  private Button cWaterInputButton;

  @FXML
  private TextField cWaterInputField;

  @FXML
  private TableView<PayRecord> cWaterTable;

  @FXML
  private TableColumn<PayRecord, Float> cWaterValueColumn;

  @FXML
  private Tab coldWaterTab;

  @FXML
  private TableColumn<PayRecord, Float> elecCostColumn;

  @FXML
  private TableColumn<PayRecord, Date> elecDateColumn;

  @FXML
  private Button elecInputButton;

  @FXML
  private TextField elecInputField;

  @FXML
  private TableView<PayRecord> elecTable;

  @FXML
  private TableColumn<PayRecord, Float> elecValueColumn;

  @FXML
  private Tab electricityTab;

  @FXML
  private TableColumn<PayRecord, Float> gasCostColumn;

  @FXML
  private TableColumn<PayRecord, Date> gasDateColumn;

  @FXML
  private Button gasInputButton;

  @FXML
  private TextField gasInputField;

  @FXML
  private Tab gasTab;

  @FXML
  private TableView<PayRecord> gasTable;

  @FXML
  private TableColumn<PayRecord, Float> gasValueColumn;

  @FXML
  private TableColumn<PayRecord, Float> hWaterCostColumn;

  @FXML
  private TableColumn<PayRecord, Date> hWaterDateColumn;

  @FXML
  private Button hWaterInputButton;

  @FXML
  private TextField hWaterInputField;

  @FXML
  private TableView<PayRecord> hWaterTable;

  @FXML
  private TableColumn<PayRecord, Float> hWaterValueColumn;

  @FXML
  private Tab hotWaterTab;

  @FXML
  private TabPane servicesTabs;



  public void initialize() throws SQLException {
    try(Connection conn = PoolConnection.getConnection()) {
      DBGetter dbg = new DBGetter(conn);

      elecDateColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Date>("payDate"));
      elecValueColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("value"));
      elecCostColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("cost"));

      hWaterDateColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Date>("payDate"));
      hWaterValueColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("value"));
      hWaterCostColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("cost"));

      cWaterDateColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Date>("payDate"));
      cWaterValueColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("value"));
      cWaterCostColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("cost"));

      gasDateColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Date>("payDate"));
      gasValueColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("value"));
      gasCostColumn.setCellValueFactory(new PropertyValueFactory<PayRecord, Float>("cost"));

      refillTable(dbg, elecTable, "electricity");
      refillTable(dbg, hWaterTable, "hot_water");
      refillTable(dbg, cWaterTable, "cold_water");
      refillTable(dbg, gasTable, "gas");

    }
  }

  public void refillTable(DBGetter dbg, TableView<PayRecord> tv, String serviceName) throws SQLException {
    tv.setItems(FXCollections.observableArrayList());
    tv.setItems(FXCollections.observableArrayList(
        dbg.getRecords(serviceName)));
  }


  @FXML
  void onColdWaterSelected(Event event) {

  }

  @FXML
  void onColdWaterTabCloseRequest(Event event) {

  }

  @FXML
  void onColdWaterTabClosed(Event event) {

  }

  @FXML
  void onElectricityTabCloseRequest(Event event) {

  }

  @FXML
  void onElectricityTabClosed(Event event) {

  }

  @FXML
  void onElectricityTabSelected(Event event) {

  }

  @FXML
  void onGasTabCloseRequest(Event event) {

  }

  @FXML
  void onGasTabClosed(Event event) {

  }

  @FXML
  void onGasTabSelected(Event event) {
  }

  @FXML
  void onHotWaterTabCloseRequest(Event event) {

  }

  @FXML
  void onHotWaterTabClosed(Event event) {

  }

  @FXML
  void onHotWaterTabSelected(Event event) {

  }

  @FXML
  void onInputButtonClick(Event event) {
    Node target = (Node) event.getTarget();
    String id = target.getId();

    if (Objects.equals(id, "elecInputButton") || Objects.equals(id, "elecInputField")) {
      float value;
      try {
        value = Float.parseFloat(elecInputField.getCharacters().toString().replace(',','.'));
      } catch(NumberFormatException e) {
        elecInputField.clear();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка ввода");
        alert.setContentText("Введённая строка не может быть преобразована в число");
        alert.showAndWait();
        return;
      }

      //TODO:Input value handler. Insert to the BD
      System.out.println(value);




    }

  }

}
