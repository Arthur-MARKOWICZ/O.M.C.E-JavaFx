package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProdutoMenu extends Application {

    @Override
    public void start(Stage stage) {
        Button btnInserir = new Button("Cadastrar Produto");
        Button btnListar = new Button("Listar Produtos");
        Button btnAtualizar = new Button("Atualizar Produto");
        Button btnExcluir = new Button("Excluir Produto");

        btnInserir.setOnAction(e -> new PaginaCadastroProduto().start(new Stage()));
        btnListar.setOnAction(e -> new PaginaListarProduto().start(new Stage()));
        btnAtualizar.setOnAction(e -> new PaginaAtualizarProduto().start(new Stage()));
        btnExcluir.setOnAction(e -> new PaginaExcluirProduto().start(new Stage()));

        VBox layout = new VBox(15, btnInserir, btnListar, btnAtualizar, btnExcluir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Menu de Produtos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

