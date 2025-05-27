package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PaginaEscolherCRUD {
    @FXML
    private Label inserir;

    @FXML
    protected void onInserir() {
        inserir.setText("em implementacao");
    }
    @FXML
    private Label consultar;

    @FXML
    protected void onConsultar() {
        consultar.setText("em implementacao");
    } @FXML
    private Label atualizar;

    @FXML
    protected void onAtualizar() {
        atualizar.setText("em implementacao");
    } @FXML
    private Label excluir;

    @FXML
    protected void onExcluir() {
        excluir.setText("em implementacao");
    }
}
