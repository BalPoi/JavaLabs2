package by.gsu.bal.finaltask;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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

      refillAllTables(dbg);

    }
  }

  public void refillTable(DBGetter dbg, TableView<PayRecord> tv, String serviceName) throws SQLException {
    tv.setItems(FXCollections.observableArrayList());
    tv.setItems(FXCollections.observableArrayList(
        dbg.getRecords(serviceName)));
  }

  public void refillAllTables(DBGetter dbg) throws SQLException {
    refillTable(dbg, elecTable, "electricity");
    refillTable(dbg, hWaterTable, "hot_water");
    refillTable(dbg, cWaterTable, "cold_water");
    refillTable(dbg, gasTable, "gas");
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
  void onInputButtonClick(Event event) throws SQLException {
    Node target = (Node) event.getTarget();
    String id = target.getId();

    if (Objects.equals(id, "elecInputButton") || Objects.equals(id, "elecInputField")) {
      inputHandler(elecInputField, "electricity");
    } else if (Objects.equals(id, "hWaterInputButton") || Objects.equals(id, "hWaterInputField")) {
      inputHandler(hWaterInputField, "hot_water");
    } else if (Objects.equals(id, "cWaterInputButton") || Objects.equals(id, "cWaterInputField")) {
      inputHandler(cWaterInputField, "cold_water");
    } else if (Objects.equals(id, "gasInputButton") || Objects.equals(id, "gasInputField")) {
      inputHandler(gasInputField, "gas");
    }

  }

  void inputHandler(TextField tf, String serviceName) throws SQLException {
    float value;
    try {
      value = Float.parseFloat(tf.getCharacters().toString().replace(',','.'));
      tf.clear();
    } catch(NumberFormatException e) {
      tf.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Ошибка");
      alert.setHeaderText("Ошибка ввода");
      alert.setContentText("Введённая строка не может быть преобразована в число");
      alert.showAndWait();
      return;
    }


    try(Connection conn = PoolConnection.getConnection()) {
      DBGetter dbg = new DBGetter(conn);
      DBSetter dbs = new DBSetter(conn);

      Optional<ButtonType> isConfirmed = showConfirmationDialog(dbg.getLastRecord(serviceName), value);
      if (isConfirmed.get() == ButtonType.OK) {
        PayRecord record = new PayRecord();
        record.setServiceId(dbg.getServiceId(serviceName));
        record.setPayDate(new Date(System.currentTimeMillis()));
        record.setValue(value);
        dbs.insertPayRecord(record);

        refillAllTables(dbg);
      }
    }


  }

  private Optional<ButtonType> showConfirmationDialog(PayRecord lastRecord, float newValue) throws SQLException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Чек");
    alert.setHeaderText("К оплате %.2f рублей\nЗа %.4f %s\nС %s".formatted(
        DBGetter.calcCost(lastRecord.getServiceName(), newValue),
        newValue - lastRecord.getValue(),
        lastRecord.getUnit(),
        lastRecord.getPayDate().toString()
    ));
    alert.setContentText("Оплатить?");

    return alert.showAndWait();
  }

//    textCost.setText(String.valueOf(dbg.calcCost(lastRecord.getServiceName(), value)));
//    textLastDate.setText(lastRecord.getPayDate().toString());
//    textValue.setText(String.valueOf(value - lastRecord.getValue()));

}
