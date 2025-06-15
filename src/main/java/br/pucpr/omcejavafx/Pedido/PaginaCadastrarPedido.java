package br.pucpr.omcejavafx.Pedido;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class PaginaCadastrarPedido extends Application {
    private static final String CAMINHO_ARQUIVO = "pedidos.dat";

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do Pedido");

        TextField valorField = new TextField();
        valorField.setPromptText("Valor");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço de Entrega");

        Button btnSalvar = new Button("Salvar Pedido");
        Button btnVoltar = new Button("Voltar");

        btnSalvar.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                double valor = Double.parseDouble(valorField.getText());
                String endereco = enderecoField.getText();

                List<Pedido> pedidosExistentes = PedidoDAO.carregarPedidos(CAMINHO_ARQUIVO);
                boolean idJaExiste = pedidosExistentes.stream()
                        .anyMatch(p -> p.getId() == id);

                if (idJaExiste) {
                    mostrarAlerta("Erro: Já existe um pedido com esse ID.", Alert.AlertType.ERROR);
                    return;
                }

                Pedido pedido = new Pedido(id, valor, endereco);
                PedidoDAO.salvarPedido(pedido, CAMINHO_ARQUIVO);
                mostrarAlerta("Pedido salvo com sucesso!", Alert.AlertType.INFORMATION);

                idField.clear();
                valorField.clear();
                enderecoField.clear();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro: ID e Valor devem ser numéricos.", Alert.AlertType.WARNING);
            } catch (Exception ex) {
                mostrarAlerta("Erro ao salvar pedido: " + ex.getMessage(), Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudPedido().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Inserir Pedido"),
                idField,
                valorField,
                enderecoField,
                btnSalvar,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Inserir Pedido");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
