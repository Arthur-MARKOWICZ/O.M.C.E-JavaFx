package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class PaginaCadastrarAvaliacaoProduto {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNota;
    @FXML
    private TextArea txtComentario;

    @FXML
    protected void onSalvar(ActionEvent event) {
        try {
            long id = Long.parseLong(txtId.getText());
            double nota = Double.parseDouble(txtNota.getText());
            String comentario = txtComentario.getText();

            AvaliarProduto avaliacao = new AvaliarProduto(id, nota, comentario);
            AvaliarProdutoDAO.salvar(avaliacao);

            System.out.println("Avaliação salva com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar avaliação: " + e.getMessage());
        }
    }
}
