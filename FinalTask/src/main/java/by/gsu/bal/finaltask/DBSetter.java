package by.gsu.bal.finaltask;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DBSetter {

  private final Connection conn;

  public DBSetter(Connection conn) {
    this.conn = conn;
  }

  public int insertPayRecord(PayRecord record) throws SQLException {
    try(Statement statement = conn.createStatement()) {
      SimpleDateFormat sdf;
      sdf = new SimpleDateFormat("yyyy-MM-dd");
      String date = sdf.format(record.getPayDate());
      return statement.executeUpdate("""
          INSERT INTO records (service_id, pay_date, meter_value)
          VALUES (%d, '%s', %s)
          """.formatted(record.getServiceId(), date, String.format(Locale.US, "%f", record.getValue())));
    }
  }

}
