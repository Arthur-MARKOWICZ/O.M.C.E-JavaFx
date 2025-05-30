package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PedidoMenu extends Application {

    @Override
    public void start(Stage stage) {
        Button btnInserir = new Button("Inserir Pedido");
        Button btnListar = new Button("Listar Pedidos");
        Button btnAtualizar = new Button("Atualizar Pedido");
        Button btnExcluir = new Button("Excluir Pedido");

        btnInserir.setOnAction(e -> new PaginaCadastrarPedido().start(new Stage()));
        btnListar.setOnAction(e -> new PaginaListarPedido().start(new Stage()));
        btnAtualizar.setOnAction(e -> new PaginaAtualizarPedido().start(new Stage()));
        btnExcluir.setOnAction(e -> new PaginaExcluirPedido().start(new Stage()));

        VBox layout = new VBox(15, btnInserir, btnListar, btnAtualizar, btnExcluir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Menu de Pedidos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
