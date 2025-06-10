package br.pucpr.omcejavafx.Pagamento;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaCadastrarPagamento {

    @FXML
    private TextField idField;

    @FXML
    private TextField metodoPagamentoField;

    @FXML
    private TextField dataField;

    @FXML
    private Label mensagemLabel;

    @FXML
    private void salvarPagamento() {
        try {
            long id = Long.parseLong(idField.getText());
            String metodo = metodoPagamentoField.getText();
            String data = dataField.getText();

            Pagamento pagamento = new Pagamento(id, metodo, data);
            PagamentoDAO.salvar(pagamento);

            mensagemLabel.setText("Pagamento salvo com sucesso!");

            idField.clear();
            metodoPagamentoField.clear();
            dataField.clear();
        } catch (NumberFormatException ex) {
            mensagemLabel.setText("Erro: ID deve ser numérico.");
        } catch (Exception ex) {
            mensagemLabel.setText("Erro ao salvar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
