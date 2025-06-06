package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaCadastrarPedido {

    @FXML
    private TextField idField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField enderecoField;

    @FXML
    private Label mensagemLabel;

    @FXML
    private void cadastrarPedido() {
        try {
            long id = Long.parseLong(idField.getText());
            double valor = Double.parseDouble(valorField.getText());
            String endereco = enderecoField.getText();

            Pedido pedido = new Pedido(id, valor, endereco);
            PedidoDAO.salvar(pedido);

            mensagemLabel.setText("Pedido salvo com sucesso!");

            idField.clear();
            valorField.clear();
            enderecoField.clear();
        } catch (NumberFormatException ex) {
            mensagemLabel.setText("Erro: ID e Valor devem ser numéricos.");
        } catch (Exception ex) {
            mensagemLabel.setText("Erro ao salvar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
