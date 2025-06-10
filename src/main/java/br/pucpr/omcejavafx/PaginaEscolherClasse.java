package br.pucpr.omcejavafx;

import br.pucpr.omcejavafx.Pagamento.PaginaEscolherCrudPagamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherClasse {
    @FXML
    private Label Pagamento;


    @FXML
    protected void onPagamento(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaEscolherCrudPagamento.class.getResource("escolherCrudPagamento-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();
    }
    @FXML
    private Label Usuario;

    @FXML
    protected void onUsuario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new PaginaEscolherCrudUsuario().start(stage);
    }
    private Label Pedido;

    @FXML
    protected void onPedido(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaEscolherCRUD.class.getResource("escolherCrudPedido-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("pedido");
        stage.setScene(escolherclasse);
        stage.show();
    } @FXML
    private Label Produto;

    @FXML
    protected void onProduto(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new PaginaEscolherCrudProduto().start(stage);
    }
    @FXML
    private Label AvaliacaoProduto;
    @FXML
    protected void onAvaliacaoProduto(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaEscolherCRUD.class.getResource("escolherCrudAvaliacaoProduto-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Avaliacao");
        stage.setScene(escolherclasse);
        stage.show();
    }
}
