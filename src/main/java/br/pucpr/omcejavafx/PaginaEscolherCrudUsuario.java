package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaEscolherCrudUsuario {

    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button btnInserir = new Button("Inserir Usuario");
        Button btnConsultar = new Button("Consultar Usuario");
        Button btnAtualizar = new Button("Atualizar Usuario");
        Button btnExcluir = new Button("Excluir Usuario");

        btnInserir.setOnAction(event -> abrirPaginaCadastro(stage));
        btnConsultar.setOnAction(event -> abrirPaginaConsulta(stage));
        btnAtualizar.setOnAction(event -> abrirPaginaAtualizacao(stage));
        btnExcluir.setOnAction(event -> abrirPaginaExclusao(stage));

        layout.getChildren().addAll(btnInserir, btnConsultar, btnAtualizar, btnExcluir);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Produto");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaCadastro(Stage stage) {
        PaginaCadastrarUsuario pagina = new PaginaCadastrarUsuario();
        pagina.start(stage);
    }

    private void abrirPaginaConsulta(Stage stage) {
        PaginaListarUsuario pagina = new PaginaListarUsuario();
        pagina.start(stage);
    }

    private void abrirPaginaAtualizacao(Stage stage) {
        PaginaAtualizarUsuario pagina = new PaginaAtualizarUsuario();
        pagina.start(stage);
    }

    private void abrirPaginaExclusao(Stage stage) {
        PaginaExcluirUsuario pagina = new PaginaExcluirUsuario();
        pagina.start(stage);
    }
    public void voltarMenuUsuario(Stage stage) {
        new PaginaEscolherCrudUsuario().start(stage);
    }
}