package br.pucpr.omcejavafx.Produto;

import br.pucpr.omcejavafx.MenuPrincipal;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaEscolherCrudProduto {

    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button btnInserir = new Button("Inserir Produto");
        Button btnConsultar = new Button("Consultar Produto");
        Button btnAtualizar = new Button("Atualizar Produto");
        Button btnExcluir = new Button("Excluir Produto");
        Button btnVoltar = new Button("Voltar");

        btnInserir.setOnAction(event -> abrirPaginaCadastro(stage));
        btnConsultar.setOnAction(event -> abrirPaginaConsulta(stage));
        btnAtualizar.setOnAction(event -> abrirPaginaAtualizacao(stage));
        btnExcluir.setOnAction(event -> abrirPaginaExclusao(stage));
        btnVoltar.setOnAction(e -> {
            try {
                new MenuPrincipal().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        layout.getChildren().addAll(btnInserir, btnConsultar, btnAtualizar, btnExcluir, btnVoltar);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Produto");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaCadastro(Stage stage) {
        PaginaCadastroProduto pagina = new PaginaCadastroProduto();
        pagina.start(stage);
    }

    private void abrirPaginaConsulta(Stage stage) {
        PaginaListarProduto pagina = new PaginaListarProduto();
        pagina.start(stage);
    }

    private void abrirPaginaAtualizacao(Stage stage) {
        PaginaAtualizarProduto pagina = new PaginaAtualizarProduto();
        pagina.start(stage);
    }

    private void abrirPaginaExclusao(Stage stage) {
        PaginaExcluirProduto pagina = new PaginaExcluirProduto();
        pagina.start(stage);
    }
    public void voltarMenuProduto(Stage stage) {
        new PaginaEscolherCrudProduto().start(stage);
    }

}
