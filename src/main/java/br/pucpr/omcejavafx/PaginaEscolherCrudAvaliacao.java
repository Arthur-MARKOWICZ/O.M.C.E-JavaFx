package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherCrudAvaliacao extends PaginaEscolherCRUD {

    @Override
    protected void onInserir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaCadastroProduto.class.getResource("ProdutoInserir-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Inserir");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    protected void onConsultar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaListarProduto.class.getResource("ProdutoListar-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Consultar");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    protected void onAtualizar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaAtualizarProduto.class.getResource("ProdutoAtualizar-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Atualizar");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    protected void onExcluir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaginaExcluirProduto.class.getResource("ProdutoExcluir-view.fxml"));
        Scene scene = new Scene(loader.load(), 500, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliar Produto - Excluir");
        stage.setScene(scene);
        stage.show();
    }
}
