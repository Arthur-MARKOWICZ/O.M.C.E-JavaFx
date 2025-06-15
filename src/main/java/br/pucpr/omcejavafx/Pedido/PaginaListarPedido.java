package br.pucpr.omcejavafx.Pedido;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaListarPedido extends Application {
    private static final String CAMINHO_ARQUIVO = "pedidos.dat";

    @Override
    public void start(Stage stage) {
        TableView<Pedido> tabela = new TableView<>();
        ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList();

        TableColumn<Pedido, String> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        TableColumn<Pedido, String> valorColuna = new TableColumn<>("Valor");
        valorColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty("R$ " + String.format("%.2f", cellData.getValue().getValor())));

        TableColumn<Pedido, String> enderecoColuna = new TableColumn<>("Endereço de Entrega");
        enderecoColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEnderecoEntrega()));

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idColuna.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        valorColuna.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        enderecoColuna.setMaxWidth(1f * Integer.MAX_VALUE * 70);

        tabela.getColumns().addAll(idColuna, valorColuna, enderecoColuna);

        try {
            List<Pedido> pedidos = PedidoDAO.carregarPedidos(CAMINHO_ARQUIVO);
            pedidosObservable.addAll(pedidos);
            tabela.setItems(pedidosObservable);
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar pedidos:\n" + e.getMessage(), Alert.AlertType.ERROR);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudPedido().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox topo = new VBox(10, btnVoltar);
        topo.setStyle("-fx-padding: 10; -fx-alignment: center;");

        BorderPane layout = new BorderPane();
        layout.setTop(topo);
        layout.setCenter(tabela);

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Lista de Pedidos");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
