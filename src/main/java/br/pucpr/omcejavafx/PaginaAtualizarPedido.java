package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaAtualizarPedido extends Application {

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do Pedido");

        TextField valorField = new TextField();
        valorField.setPromptText("Novo Valor");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Novo Endereço de Entrega");

        Button btnAtualizar = new Button("Atualizar Pedido");
        Label mensagem = new Label();

        btnAtualizar.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                double novoValor = Double.parseDouble(valorField.getText());
                String novoEndereco = enderecoField.getText();

                boolean atualizado = PedidoDAO.atualizar(id, novoValor, novoEndereco);
                if (atualizado) {
                    mensagem.setText("Pedido atualizado com sucesso.");
                } else {
                    mensagem.setText("Pedido não encontrado.");
                }
            } catch (NumberFormatException ex) {
                mensagem.setText("Erro: ID e Valor devem ser numéricos.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao atualizar pedido: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10,
                new Label("Atualizar Pedido"),
                idField,
                valorField,
                enderecoField,
                btnAtualizar,
                mensagem
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 350, 300);
        stage.setTitle("Atualizar Pedido");
        stage.setScene(scene);
        stage.show();
    }
}