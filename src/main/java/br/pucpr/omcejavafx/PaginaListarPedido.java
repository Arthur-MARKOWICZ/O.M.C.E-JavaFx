package br.pucpr.omcejavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PaginaListarPedido {

    @FXML
    private TableView<Pedido> tablePedido;

    @FXML
    private TableColumn<Pedido, String> colId;

    @FXML
    private TableColumn<Pedido, String> colValor;

    @FXML
    private TableColumn<Pedido, String> colEndereco;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("enderecoEntrega"));

        try {
            List<Pedido> pedidos = PedidoDAO.carregarTodos();
            ObservableList<Pedido> lista = FXCollections.observableArrayList(pedidos);
            tablePedido.setItems(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
