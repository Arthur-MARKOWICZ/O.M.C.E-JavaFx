package br.pucpr.omcejavafx.Pagamento;

import br.pucpr.omcejavafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherCrudPagamento  {
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button btnInserir = new Button("Inserir Pagamento");
        Button btnConsultar = new Button("Consultar Pagamento");
        Button btnAtualizar = new Button("Atualizar Pagamento");
        Button btnExcluir = new Button("Excluir Pagamento");

        btnInserir.setOnAction(event -> abrirPaginaCadastro(stage));
        btnConsultar.setOnAction(event -> abrirPaginaConsulta(stage));
        btnAtualizar.setOnAction(event -> abrirPaginaAtualizacao(stage));
        btnExcluir.setOnAction(event -> abrirPaginaExclusao(stage));

        layout.getChildren().addAll(btnInserir, btnConsultar, btnAtualizar, btnExcluir);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Pagamento");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaCadastro(Stage stage) {
        PaginaCadastroPagamento pagina = new PaginaCadastroPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaConsulta(Stage stage) {
        PaginaConsultaPagamento pagina = new PaginaConsultaPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaAtualizacao(Stage stage) {
        PaginaAtualizarPagamento pagina = new PaginaAtualizarPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaExclusao(Stage stage) {
        PaginaExcluirPagamento pagina = new PaginaExcluirPagamento();
        pagina.start(stage);
    }
}
