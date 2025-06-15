package br.pucpr.omcejavafx.Pedido;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaExcluirPedido extends Application {
    private static final String CAMINHO_ARQUIVO = "pedidos.dat";

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do Pedido");

        Button btnExcluir = new Button("Excluir Pedido");

        btnExcluir.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());

                boolean removido = PedidoDAO.excluirPedido(id, CAMINHO_ARQUIVO);
                if (removido) {
                    mostrarAlerta("Pedido excluído com sucesso.", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Pedido não encontrado.", Alert.AlertType.WARNING);
                }

                idField.clear();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro: ID deve ser numérico.", Alert.AlertType.WARNING);
            } catch (Exception ex) {
                mostrarAlerta("Erro ao excluir pedido: " + ex.getMessage(), Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudPedido().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Excluir Pedido"),
                idField,
                btnExcluir,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Excluir Pedido");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
