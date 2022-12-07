package by.gsu.bal.finaltask;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetter {

  private final Connection conn;

  public DBSetter(Connection conn) {
    this.conn = conn;
  }

  public int insertPayRecord(PayRecord record) throws SQLException {
    try(Statement statement = conn.createStatement()) {
      return statement.executeUpdate("""
          INSERT INTO records (service_id, pay_date, meter_value)
          VALUES (%d, '%s', %f)
          """.formatted(record.getServiceId(), record.getPayDate().toString(), record.getValue()));
    }
  }

}
