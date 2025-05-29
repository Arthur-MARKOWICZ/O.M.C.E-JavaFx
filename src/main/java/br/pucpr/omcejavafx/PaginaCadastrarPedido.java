package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaginaCadastrarPedido extends Application {

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do Pedido");

        TextField valorField = new TextField();
        valorField.setPromptText("Valor");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço de Entrega");

        Button btnSalvar = new Button("Salvar Pedido");
        Label mensagem = new Label();

        btnSalvar.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                double valor = Double.parseDouble(valorField.getText());
                String endereco = enderecoField.getText();

                Pedido pedido = new Pedido(id, valor, endereco);
                PedidoDAO.salvar(pedido);
                mensagem.setText("Pedido salvo com sucesso!");

                idField.clear();
                valorField.clear();
                enderecoField.clear();
            } catch (NumberFormatException ex) {
                mensagem.setText("Erro: ID e Valor devem ser numéricos.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao salvar pedido: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10,
                new Label("Inserir Pedido"),
                idField,
                valorField,
                enderecoField,
                btnSalvar,
                mensagem
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 350, 300);
        stage.setTitle("Inserir Pedido");
        stage.setScene(scene);
        stage.show();
    }
}