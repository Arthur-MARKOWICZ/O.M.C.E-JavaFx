package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsuarioMenu extends Application {

    @Override
    public void start(Stage stage) {
        Button btnInserir = new Button("Inserir Usuário");
        Button btnListar = new Button("Listar Usuário");
        Button btnAtualizar = new Button("Atualizar Usuário");
        Button btnExcluir = new Button("Excluir Usuário");
        Button btnVoltar = new Button("Voltar");

        btnInserir.setOnAction(e -> new PaginaCadastrarUsuario().start(new Stage()));
        btnListar.setOnAction(e -> new PaginaListarUsuario().start(new Stage()));
        btnAtualizar.setOnAction(e -> new PaginaAtualizarUsuario().start(new Stage()));
        btnExcluir.setOnAction(e -> new PaginaExcluirUsuario().start(new Stage()));
        btnVoltar.setOnAction(e -> {
            new MenuPrincipal().start(stage);
        });

        VBox layout = new VBox(15, btnInserir, btnListar, btnAtualizar, btnExcluir, btnVoltar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-spacing: 10;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Menu de Usuário");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

