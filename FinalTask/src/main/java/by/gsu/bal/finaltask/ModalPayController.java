package by.gsu.bal.finaltask;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class ModalPayController {
  static private float value;
  static private PayRecord lastRecord;

  @FXML
  private Text textCost;

  @FXML
  private Text textLastDate;

  @FXML
  private Text textValue;

  public void initialize() throws SQLException {
    DBGetter dbg = new DBGetter();
    textCost.setText(String.valueOf(dbg.calcCost(lastRecord.getServiceName(), value)));
    textLastDate.setText(lastRecord.getPayDate().toString());
    textValue.setText(String.valueOf(value - lastRecord.getValue()));
  }

  static public void setValue(float value) {

  }

  static public void setLastRecord(PayRecord lastRecord) {
  }

}
