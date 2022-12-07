package by.gsu.bal.finaltask;

import java.sql.*;
import java.util.ArrayList;

public class DBGetter {

  private final Connection conn;

  public DBGetter(Connection connection) {
    this.conn = connection;
  }

  public boolean areThereUnits() throws SQLException {
    try (Statement statement = conn.createStatement()) {
      return statement.executeQuery("SELECT * FROM units;").next();
    }
  }
  public boolean areThereServices() throws SQLException {
    try (Statement statement = conn.createStatement()) {
      return statement.executeQuery("SELECT * FROM services;").next();
    }
  }
  public boolean areThereRecords() throws SQLException {
    try (Statement statement = conn.createStatement()) {
      return statement.executeQuery("SELECT * FROM records;").next();
    }
  }

  public float getTariff(String serviceName) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("""
        SELECT tariff
        FROM services
        WHERE service_name = ?
        """)) {
      ps.setString(1, serviceName);
      ResultSet rs = ps.executeQuery();
      rs.next();
      return rs.getFloat("tariff");
    }
  }

  public float calcCost(String serviceName, float currValue) throws SQLException {
    PayRecord lastRecord = getLastRecord(serviceName);
    return (float) (Math.round((currValue - lastRecord.getValue()) * getTariff(serviceName) * 100.0) / 100.0);
  }
  public float calcCost(String serviceName, float currValue, long currRecordId) throws SQLException {
    PayRecord lastRecord = getLastRecord(serviceName, currRecordId);
    return (float) (Math.round((currValue - lastRecord.getValue()) * getTariff(serviceName) * 100.0) / 100.0);
  }

  public ArrayList<PayRecord> getRecords(String serviceName) throws SQLException {
    ArrayList<PayRecord> records = new ArrayList<>();
    try (PreparedStatement ps = conn.prepareStatement("""
        SELECT record_id, service_name, records.service_id, pay_date, meter_value, units.unit_name, tariff FROM
        records JOIN services ON records.service_id = services.service_id
        JOIN units ON services.unit_id = units.unit_id
        WHERE service_name = ?
        """)) {
      ps.setString(1, serviceName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        records.add(new PayRecord(
            rs.getLong("record_id"),
            rs.getString("service_name"),
            rs.getInt("service_id"),
            rs.getDate("pay_date"),
            rs.getFloat("meter_value"),
            rs.getString("unit_name"),
            calcCost(rs.getString("service_name"), rs.getFloat("meter_value"), rs.getLong("record_id"))
        ));
      }
    }
    return records;
  }

  public PayRecord getLastRecord(String serviceName) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("""
        SELECT record_id, service_name, records.service_id, pay_date, meter_value, units.unit_name, tariff FROM
        records JOIN services ON records.service_id = services.service_id
        JOIN units ON services.unit_id = units.unit_id
        WHERE service_name = ?
        ORDER BY record_id DESC
        LIMIT 1
        """)) {
      ps.setString(1, serviceName);
      ResultSet rs = ps.executeQuery();
      rs.next();
      return new PayRecord(
          rs.getLong("record_id"),
          rs.getString("service_name"),
          rs.getInt("service_id"),
          rs.getDate("pay_date"),
          rs.getFloat("meter_value"),
          rs.getString("unit_name"),
          0
      );
    }
  }
  public PayRecord getLastRecord(String serviceName, long currRecordId) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("""
        SELECT record_id, service_name, records.service_id, pay_date, meter_value, units.unit_name, tariff FROM
        records JOIN services ON records.service_id = services.service_id
        JOIN units ON services.unit_id = units.unit_id
        WHERE service_name = ? AND record_id < ?
        ORDER BY record_id DESC
        LIMIT 1
        """)) {
      ps.setString(1, serviceName);
      ps.setLong(2, currRecordId);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) return new PayRecord(0, serviceName, 0, null, 0, null, 0);
      return new PayRecord(
          rs.getLong("record_id"),
          rs.getString("service_name"),
          rs.getInt("service_id"),
          rs.getDate("pay_date"),
          rs.getFloat("meter_value"),
          rs.getString("unit_name"),
          calcCost(rs.getString("service_name"), rs.getFloat("meter_value"))
      );
    }
  }

}
