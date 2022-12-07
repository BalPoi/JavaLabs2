package by.gsu.bal.finaltask;

public class Constants {

  public static final String CREATE_RECORDS_TABLE = """
      CREATE TABLE IF NOT EXISTS records (
        record_id bigint NOT NULL AUTO_INCREMENT,
        service_id int NOT NULL,
        pay_date date NOT NULL,
        meter_value float NOT NULL,
        PRIMARY KEY (record_id),
        FOREIGN KEY (service_id) REFERENCES services (service_id)
        ON DELETE CASCADE
      );
      """;
  public static final String CREATE_SERVICES_TABLE = """
      CREATE TABLE IF NOT EXISTS services (
        service_id int NOT NULL AUTO_INCREMENT,
        service_name varchar(32) NOT NULL,
        unit_id int NOT NULL,
        tariff float NOT NULL,
        PRIMARY KEY (service_id),
        FOREIGN KEY (unit_id) REFERENCES units (unit_id)
        ON DELETE CASCADE
      );
      """;
  public static final String CREATE_UNITS_TABLE = """
      CREATE TABLE IF NOT EXISTS units (
        unit_id int NOT NULL AUTO_INCREMENT,
        unit_name varchar(16) NOT NULL,
        PRIMARY KEY (unit_id)
      );
      """;

  public static final String INSERT_UNITS = """
      INSERT INTO units (unit_name) VALUES ('куб.метр');
      INSERT INTO units (unit_name) VALUES ('кВт*ч');
      """;
  public static final String INSERT_SERVICES = """
      INSERT INTO services (service_name, unit_id, tariff) VALUES ('electricity', 2, 0.2321);
      INSERT INTO services (service_name, unit_id, tariff) VALUES ('gas', 1, 0.1994);
      INSERT INTO services (service_name, unit_id, tariff) VALUES ('hot_water', 1, 1.1927);
      INSERT INTO services (service_name, unit_id, tariff) VALUES ('cold_water', 1, 2.0);
      """;
  public static final String INSERT_RECORDS = """
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (1, '2022-06-20', 21.29);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (1, '2022-07-20', 25.29);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (1, '2022-08-20', 30.29);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (1, '2022-09-20', 33.29);
      
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (2, '2022-06-21', 40.48);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (2, '2022-07-21', 42.48);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (2, '2022-08-21', 46.48);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (2, '2022-09-21', 46.48);
      
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (3, '2022-06-19', 15.88);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (3, '2022-07-19', 16.88);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (3, '2022-08-19', 17.88);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (3, '2022-09-19', 18.88);
      
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (4, '2022-06-25', 20.11);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (4, '2022-07-25', 24.11);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (4, '2022-08-25', 28.11);
      INSERT INTO records (service_id, pay_date, meter_value) VALUES (4, '2022-09-25', 33.11);
      """;

}
