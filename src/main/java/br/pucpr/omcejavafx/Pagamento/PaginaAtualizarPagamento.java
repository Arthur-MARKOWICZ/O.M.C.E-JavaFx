package br.pucpr.omcejavafx.Pagamento;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaAtualizarPagamento extends VoltarPaginaPagamentoCrud {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtMetodo;

    @FXML
    private TextField txtData;

    @FXML
    private Label lblMensagem;

    private PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public void carregarPagamento(Pagamento pagamento) {
        txtId.setText(String.valueOf(pagamento.getId()));
        txtMetodo.setText(pagamento.getMetodoPagamento());
        txtData.setText(pagamento.getData());
        lblMensagem.setText("");
    }

    @FXML
    private void onAtualizar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String metodo = txtMetodo.getText();
            String data = txtData.getText();

            if (metodo.isEmpty() || data.isEmpty()) {
                lblMensagem.setStyle("-fx-text-fill: red;");
                lblMensagem.setText("Preencha todos os campos.");
                return;
            }

            Pagamento pagamento = new Pagamento(id, metodo, data);
            boolean atualizado = pagamentoDAO.atualizar(pagamento);

            lblMensagem.setStyle("-fx-text-fill: green;");
            lblMensagem.setText("Pagamento atualizado com sucesso!");

        } catch (NumberFormatException e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("ID inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro ao atualizar pagamento.");
        }
    }

}
