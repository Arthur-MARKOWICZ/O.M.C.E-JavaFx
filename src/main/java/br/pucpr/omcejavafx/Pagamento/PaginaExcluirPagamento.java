package br.pucpr.omcejavafx;

import br.pucpr.omcejavafx.Pagamento.PagamentoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaExcluirPagamento extends VoltarPaginaPagamentoCrud {
    @FXML
    private TextField txtId;

    @FXML
    private Label lblMensagem;

    @FXML
    private void onExcluir() {
        try {
            int id = Integer.parseInt(txtId.getText());

            boolean excluido = PagamentoDAO.excluir(id);

            if (excluido) {
                lblMensagem.setStyle("-fx-text-fill: green;");
                lblMensagem.setText("Pagamento excluído com sucesso!");

            } else {
                lblMensagem.setStyle("-fx-text-fill: red;");
                lblMensagem.setText("Pagamento com ID " + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("ID inválido.");
        } catch (Exception e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro ao excluir pagamento.");
            e.printStackTrace();
        }
    }
}
