package br.pucpr.omcejavafx.Avaliacao;

import br.pucpr.omcejavafx.MenuPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaEscolherCrudAvaliacao extends Application {

    @Override
    public void start(Stage stage) {
        Button btnInserir = new Button("Inserir Avaliação");
        Button btnListar = new Button("Consultar Avaliação");
        Button btnAtualizar = new Button("Atualizar Avaliação");
        Button btnExcluir = new Button("Excluir Avaliação");
        Button btnVoltar = new Button("Voltar");

        btnInserir.setOnAction(e -> {
            try {
                //new PaginaCadastrarAvaliacaoProduto().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnListar.setOnAction(e -> {
            try {
                //new PaginaListarAvaliacaoProduto().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnAtualizar.setOnAction(e -> {
            try {
                //new PaginaAtualizarAvaliacaoProduto().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnExcluir.setOnAction(e -> {
            try {
                //new PaginaExcluirAvaliacaoProduto().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnVoltar.setOnAction(e -> {
            try {
                new MenuPrincipal().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(15, btnInserir, btnListar, btnAtualizar, btnExcluir, btnVoltar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Avaliação Produto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
