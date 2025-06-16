package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherCrudAvaliacao extends MenuPrincipal {

    protected void onInserir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaCadastrarAvaliacaoProduto.class.getResource("ProdutoInserir-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Inserir");
        stage.setScene(scene);
        stage.show();
    }

    protected void onConsultar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaListarAvaliacaoProduto.class.getResource("ProdutoListar-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Consultar");
        stage.setScene(scene);
        stage.show();
    }

    protected void onAtualizar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaAtualizarAvaliacaoProduto.class.getResource("ProdutoAtualizar-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Atualizar");
        stage.setScene(scene);
        stage.show();
    }

    protected void onExcluir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaExcluirAvaliacaoProduto.class.getResource("ProdutoExcluir-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Excluir");
        stage.setScene(scene);
        stage.show();
    }
}
