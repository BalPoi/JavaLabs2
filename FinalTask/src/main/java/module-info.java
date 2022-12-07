module by.gsu.bal.finaltask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
  requires com.h2database;
  requires java.sql;

  opens by.gsu.bal.finaltask to javafx.fxml;
    exports by.gsu.bal.finaltask;
}