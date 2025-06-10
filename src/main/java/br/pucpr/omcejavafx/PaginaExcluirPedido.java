package br.pucpr.omcejavafx;

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
        Label mensagem = new Label();

        btnExcluir.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());

                boolean removido = PedidoDAO.excluirPedido(id, CAMINHO_ARQUIVO);
                if (removido) {
                    mensagem.setText("Pedido excluído com sucesso.");
                } else {
                    mensagem.setText("Pedido não encontrado.");
                }

                idField.clear();
            } catch (NumberFormatException ex) {
                mensagem.setText("Erro: ID deve ser numérico.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao excluir pedido: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10,
                new Label("Excluir Pedido"),
                idField,
                btnExcluir,
                mensagem
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Excluir Pedido");
        stage.setScene(scene);
        stage.show();
    }
}
