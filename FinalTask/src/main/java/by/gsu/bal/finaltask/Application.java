package by.gsu.bal.finaltask;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 720, 440);
    stage.setTitle("Платёжечка");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    DB db = new DB();
    try {
      db.init();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    launch();
  }
}