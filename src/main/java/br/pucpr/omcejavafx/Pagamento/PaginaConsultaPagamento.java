package br.pucpr.omcejavafx.Pagamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PaginaConsultaPagamento {
    @FXML
    private TableView<Pagamento> tablePagamento;

    @FXML
    private TableColumn<Pagamento, String> colId;

    @FXML
    private TableColumn<Pagamento, String> colMetodo;

    @FXML
    private TableColumn<Pagamento, String> colData;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMetodo.setCellValueFactory(new PropertyValueFactory<>("metodoPagamento"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        try {
            List<Pagamento> pagamentos = PagamentoDAO.listar();
            ObservableList<Pagamento> lista = FXCollections.observableArrayList(pagamentos);
            tablePagamento.setItems(lista);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
