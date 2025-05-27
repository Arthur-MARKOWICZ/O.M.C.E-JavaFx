package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PaginaEscolherClasse {
    @FXML
    private Label Pagamento;

    @FXML
    protected void onPagamento() {
        Pagamento.setText("Indo para a pagina de crud do pagamento");
    }
    @FXML
    private Label Usuario;

    @FXML
    protected void onUsuario() {
        Pagamento.setText("Indo para a pagina de crud do usuario");
    } @FXML
    private Label pedido;

    @FXML
    protected void onPedido() {
        Pagamento.setText("Indo para a pagina de crud do pedido");
    } @FXML
    private Label produto;

    @FXML
    protected void onProduto() {
        Pagamento.setText("Indo para a pagina de crud do produto");
    }

}
