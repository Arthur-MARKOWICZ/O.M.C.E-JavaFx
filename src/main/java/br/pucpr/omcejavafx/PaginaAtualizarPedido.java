package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaAtualizarPedido {

    @FXML
    private TextField idField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField enderecoField;

    @FXML
    private Label mensagemLabel;

    @FXML
    private void atualizarPedido() {
        try {
            long id = Long.parseLong(idField.getText());
            double novoValor = Double.parseDouble(valorField.getText());
            String novoEndereco = enderecoField.getText();

            boolean atualizado = PedidoDAO.atualizar(id, novoValor, novoEndereco);
            if (atualizado) {
                mensagemLabel.setText("Pedido atualizado com sucesso.");

                idField.clear();
                valorField.clear();
                enderecoField.clear();
            } else {
                mensagemLabel.setText("Pedido não encontrado.");
            }
        } catch (NumberFormatException ex) {
            mensagemLabel.setText("Erro: ID e Valor devem ser numéricos.");
        } catch (Exception ex) {
            mensagemLabel.setText("Erro ao atualizar pedido: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void onCancelar() {
        // Fecha a janela atual
        idField.getScene().getWindow().hide();
    }
}
