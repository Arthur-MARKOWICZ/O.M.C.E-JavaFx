package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class PaginaAtualizarAvaliacaoProduto {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNota;

    @FXML
    private TextArea txtComentario;

    @FXML
    protected void onAtualizar() {
        try {
            long id = Long.parseLong(txtId.getText());
            double novaNota = Double.parseDouble(txtNota.getText());
            String novoComentario = txtComentario.getText();

            List<AvaliarProduto> avaliacoes = AvaliarProdutoDAO.carregarTodos();
            boolean atualizado = false;

            for (AvaliarProduto ap : avaliacoes) {
                if (ap.getId() == id) {
                    ap.setNota(novaNota);
                    ap.setComentario(novoComentario);
                    atualizado = true;
                    break;
                }
            }

            if (atualizado) {
                AvaliarProdutoDAO.salvarTodos(avaliacoes);
                mostrarAlerta("Sucesso", "Avaliação atualizada com sucesso.");
            } else {
                mostrarAlerta("Erro", "Avaliação com ID " + id + " não encontrada.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "ID e Nota devem ser numéricos.");
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao acessar o arquivo de avaliações.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
