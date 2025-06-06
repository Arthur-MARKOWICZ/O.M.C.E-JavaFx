package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaEscolherCrudPedido extends PaginaEscolherCRUD{
    @FXML
    private Label teste;
    @Override
    protected void onInserir(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaCadastrarPedido.class.getResource("PedidoInserir-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pedido");
        stage.setScene(escolherclasse);
        stage.show();
    }

    @Override
    protected void onConsultar(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaListarPedido.class.getResource("PedidoListar-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pedido");
        stage.setScene(escolherclasse);
        stage.show();

    }

    @Override
    protected void onAtualizar(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaAtualizarPedido.class.getResource("PedidoAtualizar-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pedido");
        stage.setScene(escolherclasse);
        stage.show();
    }

    @Override
    protected void onExcluir(ActionEvent event) throws IOException {
        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaExcluirPedido.class.getResource("PedidoExcluir-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pedido");
        stage.setScene(escolherclasse);
        stage.show();
    }
}
