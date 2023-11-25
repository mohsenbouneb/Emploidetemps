package com.example.miniprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Emploi.class.getResource("face1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 575);
        stage.setTitle("Emploi du Temps");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static class Face2Controller {

        public void openFace2() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("face2.fxml"));
                Parent root = loader.load();
Requetes requetes = loader.getController();
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.setTitle("REQUETES");
                stage2.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
