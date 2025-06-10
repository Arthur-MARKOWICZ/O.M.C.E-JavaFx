package br.pucpr.omcejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    @FXML
    protected void onVoltar(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherClasse = new FXMLLoader(
                PaginaEscolherClasse.class.getResource("escolherClasse-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherClasse.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Escolher a entidade");
        stage.setScene(escolherclasse);
        stage.show();
    }
}
