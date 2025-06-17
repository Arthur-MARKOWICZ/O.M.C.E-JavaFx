package br.pucpr.omcejavafx.Avaliacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaAtualizarAvaliacaoProduto extends Application {
    private static final String CAMINHO_ARQUIVO = "avaliacoes.dat";

    private TextField idField;
    private TextField notaField;
    private TextField comentarioField;
    private AvaliarProduto avaliacaoAtual;

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Atualizar Avaliação");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        idField = new TextField();
        idField.setPromptText("ID da Avaliação");

        notaField = new TextField();
        notaField.setPromptText("Nova Nota (0 a 10)");

        comentarioField = new TextField();
        comentarioField.setPromptText("Novo Comentário");

        Button btnBuscar = new Button("Buscar Avaliação");
        btnBuscar.setOnAction(e -> buscarAvaliacao());

        Button btnAtualizar = new Button("Atualizar Avaliação");
        btnAtualizar.setOnAction(e -> atualizarAvaliacao());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudAvaliacao().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                titulo,
                idField,
                btnBuscar,
                notaField,
                comentarioField,
                btnAtualizar,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 350);
        stage.setTitle("Atualizar Avaliação");
        stage.setScene(scene);
        stage.show();
    }

    private void buscarAvaliacao() {
        try {
            long id = Long.parseLong(idField.getText().trim());
            List<AvaliarProduto> avaliacoes = AvaliarProdutoDAO.carregarAvaliacao(CAMINHO_ARQUIVO);

            for (AvaliarProduto a : avaliacoes) {
                if (a.getId() == id) {
                    avaliacaoAtual = a;
                    notaField.setText(String.valueOf(a.getNota()));
                    comentarioField.setText(a.getComentario());
                    return;
                }
            }

            mostrarAlerta("Avaliação não encontrada.", Alert.AlertType.WARNING);

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro: ID deve ser numérico.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Erro ao buscar avaliação: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void atualizarAvaliacao() {
        if (avaliacaoAtual == null) {
            mostrarAlerta("Nenhuma avaliação carregada. Use 'Buscar Avaliação' primeiro.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double novaNota = Double.parseDouble(notaField.getText().trim());
            String novoComentario = comentarioField.getText().trim();

            if (novoComentario.isEmpty()) {
                mostrarAlerta("O comentário não pode estar vazio.", Alert.AlertType.WARNING);
                return;
            }

            AvaliarProduto novaAvaliacao = new AvaliarProduto(avaliacaoAtual.getId(), novaNota, novoComentario);
            AvaliarProdutoDAO.atualizarAvaliacao(novaAvaliacao, CAMINHO_ARQUIVO);

            mostrarAlerta("Avaliação atualizada com sucesso!", Alert.AlertType.INFORMATION);

            idField.clear();
            notaField.clear();
            comentarioField.clear();
            avaliacaoAtual = null;

        } catch (NumberFormatException ex) {
            mostrarAlerta("Nota deve ser um número entre 0 e 10.", Alert.AlertType.WARNING);
        } catch (IllegalArgumentException ex) {
            mostrarAlerta("Erro: " + ex.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception ex) {
            mostrarAlerta("Erro ao atualizar avaliação: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
