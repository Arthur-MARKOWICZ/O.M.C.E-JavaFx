package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaListarPedido extends Application {

    @Override
    public void start(Stage stage) {
        TextArea area = new TextArea();
        area.setEditable(false);

        try {
            List<Pedido> pedidos = PedidoDAO.carregarTodos();
            if (pedidos.isEmpty()) {
                area.setText("Nenhum pedido encontrado.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Pedido p : pedidos) {
                    sb.append("ID: ").append(p.getId())
                            .append(" | Valor: ").append(p.getValor())
                            .append(" | Endereço: ").append(p.getEnderecoEntrega())
                            .append("\n");
                }
                area.setText(sb.toString());
            }
        } catch (Exception e) {
            area.setText("Erro ao carregar pedidos: " + e.getMessage());
        }

        VBox layout = new VBox(10, area);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Lista de Pedidos");
        stage.setScene(scene);
        stage.show();
    }
}