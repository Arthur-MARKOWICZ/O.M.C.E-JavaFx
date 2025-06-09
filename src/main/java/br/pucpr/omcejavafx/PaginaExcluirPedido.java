package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaExcluirPedido {

    @FXML
    private TextField txtId;

    @FXML
    private Label lblMensagem;

    @FXML
    private void onExcluir() {
        try {
            long id = Long.parseLong(txtId.getText());

            boolean removido = PedidoDAO.excluir(id);
            if (removido) {
                lblMensagem.setText("Pedido excluído com sucesso.");
            } else {
                lblMensagem.setText("Pedido não encontrado.");
            }

            txtId.clear();
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Erro: ID deve ser numérico.");
        } catch (Exception ex) {
            lblMensagem.setText("Erro ao excluir pedido: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}