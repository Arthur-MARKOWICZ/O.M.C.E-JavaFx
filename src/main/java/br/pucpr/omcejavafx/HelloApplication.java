package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader paginaEscolherClasse = new FXMLLoader(PaginaEscolherClasse.class
                .getResource("escolherClasse-view.fxml"));
        Scene escolherclasse = new Scene(paginaEscolherClasse.load(), 500, 500);
        stage.setTitle("Escolher a entidade");
        stage.setScene(escolherclasse);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}