package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsuarioMenu extends Application {

    @Override
    public void start(Stage stage) {
        Button btnInserir = new Button("Inserir Usuario");
        Button btnListar = new Button("Listar Usuario");
        Button btnAtualizar = new Button("Atualizar Usuario");
        Button btnExcluir = new Button("Excluir Usuario");

        btnInserir.setOnAction(e -> new PaginaCadastrarUsuario().start(new Stage()));
        btnListar.setOnAction(e -> new PaginaListarUsuario().start(new Stage()));
        btnAtualizar.setOnAction(e -> new PaginaAtualizarUsuario().start(new Stage()));
        btnExcluir.setOnAction(e -> new PaginaExcluirUsuario().start(new Stage()));

        VBox layout = new VBox(15, btnInserir, btnListar, btnAtualizar, btnExcluir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Menu de Usuario");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

