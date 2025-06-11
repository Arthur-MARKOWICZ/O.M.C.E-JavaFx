package br.pucpr.omcejavafx.Pagamento;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class PaginaConsultaPagamento extends Application {

    @Override
    public void start(Stage stage) {
        TableView<Pagamento> tabela = new TableView<>();
        ObservableList<Pagamento> Pagamentos = FXCollections.observableArrayList();

        TableColumn<Pagamento, String> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        TableColumn<Pagamento, String> metodoDePagamentoColuna = new TableColumn<>("Metodo de pagamento");
        metodoDePagamentoColuna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty
                (cellData.getValue().getMetodoPagamento()));

        TableColumn<Pagamento, String> dataColuna = new TableColumn<>("Data");
        dataColuna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty
                (cellData.getValue().getData()));



        tabela.getColumns().addAll(idColuna, metodoDePagamentoColuna, dataColuna);

        try {
            List<Pagamento> lista = PagamentoDAO.listar();
            Pagamentos.addAll(lista);
            tabela.setItems(Pagamentos);
        } catch (Exception e) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao carregar produtos");
            erro.setHeaderText(null);
            erro.setContentText("Erro: " + e.getMessage());
            erro.showAndWait();
        }

        BorderPane layout = new BorderPane();
        layout.setCenter(tabela);
        Scene scene = new Scene(layout, 800, 600);

        stage.setTitle("Lista de Pagamentos Cadastrados");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
