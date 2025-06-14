package br.pucpr.omcejavafx.Pedido;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaEscolherCrudPedido {

    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button btnInserir = new Button("Inserir Pedido");
        Button btnConsultar = new Button("Consultar Pedido");
        Button btnAtualizar = new Button("Atualizar Pedido");
        Button btnExcluir = new Button("Excluir Pedido");

        btnInserir.setOnAction(event -> abrirPaginaCadastro(stage));
        btnConsultar.setOnAction(event -> abrirPaginaConsulta(stage));
        btnAtualizar.setOnAction(event -> abrirPaginaAtualizacao(stage));
        btnExcluir.setOnAction(event -> abrirPaginaExclusao(stage));

        layout.getChildren().addAll(btnInserir, btnConsultar, btnAtualizar, btnExcluir);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Pedido");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaCadastro(Stage stage) {
        PaginaCadastrarPedido pagina = new PaginaCadastrarPedido();
        pagina.start(stage);
    }

    private void abrirPaginaConsulta(Stage stage) {
        PaginaListarPedido pagina = new PaginaListarPedido();
        pagina.start(stage);
    }

    private void abrirPaginaAtualizacao(Stage stage) {
        PaginaAtualizarPedido pagina = new PaginaAtualizarPedido();
        pagina.start(stage);
    }

    private void abrirPaginaExclusao(Stage stage) {
        PaginaExcluirPedido pagina = new PaginaExcluirPedido();
        pagina.start(stage);
    }
}
