package br.pucpr.omcejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class PaginaListarAvaliacaoProduto {

    @FXML
    private TextArea txtListaAvaliacoes;

    @FXML
    public void initialize() {
        try {
            var avaliacoes = AvaliarProdutoDAO.carregarTodos();
            StringBuilder sb = new StringBuilder();

            for (AvaliarProduto ap : avaliacoes) {
                sb.append("ID: ").append(ap.getId())
                        .append(", Nota: ").append(ap.getNota())
                        .append(", Comentário: ").append(ap.getComentario())
                        .append(", Data: ").append(ap.getDataAtual()).append("\n");
            }

            txtListaAvaliacoes.setText(sb.toString());

        } catch (IOException e) {
            txtListaAvaliacoes.setText("Erro ao carregar avaliações: " + e.getMessage());
        }
    }
}
