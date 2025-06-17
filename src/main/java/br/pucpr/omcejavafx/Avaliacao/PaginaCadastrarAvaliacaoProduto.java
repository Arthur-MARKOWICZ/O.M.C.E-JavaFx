package br.pucpr.omcejavafx.Avaliacao;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaCadastrarAvaliacaoProduto {
    private static final String CAMINHO_ARQUIVO = "avaliacoes.dat";

    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do Produto");

        TextField notaField = new TextField();
        notaField.setPromptText("Nota (0 a 10)");

        TextField comentarioField = new TextField();
        comentarioField.setPromptText("Comentário");

        Button btnSalvar = new Button("Salvar Avaliação");
        Button btnVoltar = new Button("Voltar");

        btnSalvar.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                double nota = Double.parseDouble(notaField.getText());
                String comentario = comentarioField.getText();

                if (idField.getText().isEmpty() || notaField.getText().isEmpty() || comentarioField.getText().isEmpty()) {
                    mostrarAlerta("Preencha todos os campos", Alert.AlertType.WARNING);
                    return;
                }

                if (nota < 0 || nota > 10) {
                    mostrarAlerta("A nota deve estar entre 0 e 10.", Alert.AlertType.WARNING);
                    return;
                }

                List<AvaliarProduto> avaliacoes = AvaliarProdutoDAO.carregarAvaliacao(CAMINHO_ARQUIVO);
                boolean idJaExiste = avaliacoes.stream().anyMatch(a -> a.getId() == id);

                if (idJaExiste) {
                    mostrarAlerta("Erro: Já existe uma avaliação com esse ID.", Alert.AlertType.ERROR);
                    return;
                }

                AvaliarProduto avaliacao = new AvaliarProduto(id, nota, comentario);
                AvaliarProdutoDAO.salvarAvaliacao(avaliacao, CAMINHO_ARQUIVO);
                mostrarAlerta("Avaliação salva com sucesso!", Alert.AlertType.INFORMATION);

                idField.clear();
                notaField.clear();
                comentarioField.clear();

            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro: ID e Nota devem ser numéricos.", Alert.AlertType.WARNING);
            } catch (Exception ex) {
                mostrarAlerta("Erro ao salvar avaliação: " + ex.getMessage(), Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudAvaliacao().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Inserir Avaliação de Produto"),
                idField,
                notaField,
                comentarioField,
                btnSalvar,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Cadastrar Avaliação");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
