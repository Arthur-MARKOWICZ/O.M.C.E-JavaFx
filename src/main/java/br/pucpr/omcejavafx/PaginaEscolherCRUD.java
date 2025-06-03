package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class PaginaEscolherCRUD {
    @FXML
    private Label inserir;

    @FXML
    protected void onInserir(ActionEvent event) throws IOException {
        inserir.setText("em implementacao");
    }
    @FXML
    private Label consultar;

    @FXML
    protected void onConsultar(ActionEvent event) throws IOException {
        consultar.setText("em implementacao");
    } @FXML
    private Label atualizar;

    @FXML
    protected void onAtualizar(ActionEvent event) throws IOException {
        atualizar.setText("em implementacao");
    } @FXML
    private Label excluir;

    @FXML
    protected void onExcluir(ActionEvent event) throws IOException {
        excluir.setText("em implementacao");
    }
}
