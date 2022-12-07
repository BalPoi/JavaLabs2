package by.gsu.bal.finaltask;

import by.gsu.bal.finaltask.Constants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DB {

  public DB() {
  }

  public void init() throws SQLException {
    ResourceBundle rb;
    try {
      rb = ResourceBundle.getBundle("by.gsu.bal.finaltask.config");
    } catch (
    MissingResourceException e) {
      throw new RuntimeException("The config.properties file not found");
    }

    var url = rb.getString("db.jdbcUrl");
    var user = rb.getString("db.user");
    var password = rb.getString("db.password");

    System.out.println("=========================================================================================");
    System.out.printf("Trying to connect to the database(%s) as %s@%s...\n", url, user, password);

    PoolConnection.init(url, user, password);

    try(Connection conn = PoolConnection.getConnection()) {
      boolean isValid = conn.isValid(0);
      System.out.printf("%s\n", isValid ? "Success\n" : "Fail\n");
      if (!isValid) throw new RuntimeException("Failed to connect to the database.");

      try (Statement statement = conn.createStatement()) {
        statement.execute(Constants.CREATE_UNITS_TABLE);
        statement.execute(Constants.CREATE_SERVICES_TABLE);
        statement.execute(Constants.CREATE_RECORDS_TABLE);
      }

      DBGetter dbg = new DBGetter(conn);

      if (!dbg.areThereUnits()) {
        try (Statement statement = conn.createStatement()) {
          statement.execute(Constants.INSERT_UNITS);
        }
      }
      if (!dbg.areThereServices()) {
        try (Statement statement = conn.createStatement()) {
          statement.execute(Constants.INSERT_SERVICES);
        }
      }
      if (!dbg.areThereRecords()) {
        try (Statement statement = conn.createStatement()) {
          statement.execute(Constants.INSERT_RECORDS);
        }
      }

    }
  }

}
