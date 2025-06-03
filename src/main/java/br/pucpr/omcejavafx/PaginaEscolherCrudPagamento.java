package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherCrudPagamento extends PaginaEscolherCRUD{
    @FXML
    private Label teste;
    @Override
    protected void onInserir(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaCadastrarPagamento.class.getResource("PagamentoInserir-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();
    }

    @Override
    protected void onConsultar(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaConsultaPagamento.class.getResource("PagamentoConsultar-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();

    }

    @Override
    protected void onAtualizar(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaAtualizarPagamento.class.getResource("PagamentoAtualizar-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();
    }

    @Override
    protected void onExcluir(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaExcluirPagamento.class.getResource("PagamentoExcluir-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();
    }
}
