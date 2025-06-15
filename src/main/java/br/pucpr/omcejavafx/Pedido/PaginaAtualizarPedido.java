package br.pucpr.omcejavafx.Pedido;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaAtualizarPedido extends Application {
    private static final String CAMINHO_ARQUIVO = "pedidos.dat";

    private TextField idField;
    private TextField valorField;
    private TextField enderecoField;
    private Pedido pedidoAtual;

    @Override
    public void start(Stage stage) {
        idField = new TextField();
        idField.setPromptText("ID do Pedido");

        valorField = new TextField();
        valorField.setPromptText("Novo Valor");

        enderecoField = new TextField();
        enderecoField.setPromptText("Novo Endereço de Entrega");

        Button btnBuscar = new Button("Buscar Pedido");
        btnBuscar.setOnAction(e -> buscarPedido());

        Button btnAtualizar = new Button("Atualizar Pedido");
        btnAtualizar.setOnAction(e -> atualizarPedido());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudPedido().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Atualizar Pedido"),
                idField,
                btnBuscar,
                valorField,
                enderecoField,
                btnAtualizar,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Atualizar Pedido");
        stage.setScene(scene);
        stage.show();
    }

    private void buscarPedido() {
        try {
            long id = Long.parseLong(idField.getText());
            List<Pedido> pedidos = PedidoDAO.carregarPedidos(CAMINHO_ARQUIVO);

            for (Pedido p : pedidos) {
                if (p.getId() == id) {
                    pedidoAtual = p;
                    valorField.setText(String.valueOf(p.getValor()));
                    enderecoField.setText(p.getEnderecoEntrega());
                    return;
                }
            }

            mostrarAlerta("Pedido não encontrado.", Alert.AlertType.WARNING);

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro: ID deve ser numérico.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Erro ao buscar pedido: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void atualizarPedido() {
        if (pedidoAtual == null) {
            mostrarAlerta("Nenhum pedido carregado.", Alert.AlertType.ERROR);
            return;
        }
        try {
            long id = Long.parseLong(idField.getText());
            double novoValor = Double.parseDouble(valorField.getText());
            String novoEndereco = enderecoField.getText();

            Pedido pedidoAtualizado = new Pedido(id, novoValor, novoEndereco);
            PedidoDAO.atualizarPedido(pedidoAtualizado, CAMINHO_ARQUIVO);

            mostrarAlerta("Pedido atualizado com sucesso.", Alert.AlertType.INFORMATION);

            idField.clear();
            valorField.clear();
            enderecoField.clear();
            pedidoAtual = null;

        } catch (NumberFormatException ex) {
            mostrarAlerta("Erro: ID e Valor devem ser numéricos.", Alert.AlertType.WARNING);
        } catch (Exception ex) {
            mostrarAlerta("Erro ao atualizar pedido: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
