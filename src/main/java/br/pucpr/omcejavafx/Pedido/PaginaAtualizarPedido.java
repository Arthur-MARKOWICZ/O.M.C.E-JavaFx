package br.pucpr.omcejavafx.Pedido;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaAtualizarPedido extends Application {
    private static final String CAMINHO_ARQUIVO = "pedidos.dat";

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

                Pedido pedidoAtualizado = new Pedido(id, novoValor, novoEndereco);
                PedidoDAO.atualizarPedido(pedidoAtualizado, CAMINHO_ARQUIVO);

                mensagem.setText("Pedido atualizado com sucesso.");

                idField.clear();
                valorField.clear();
                enderecoField.clear();

            } catch (NumberFormatException ex) {
                mensagem.setText("Erro: ID e Valor devem ser numéricos.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao atualizar pedido: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PedidoMenu().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Atualizar Pedido"),
                idField,
                valorField,
                enderecoField,
                btnAtualizar,
                mensagem,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Atualizar Pedido");
        stage.setScene(scene);
        stage.show();
    }
}
