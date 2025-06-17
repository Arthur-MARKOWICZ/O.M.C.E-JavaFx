package br.pucpr.omcejavafx.Avaliacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaExcluirAvaliacaoProduto extends Application {
    private static final String CAMINHO_ARQUIVO = "avaliacoes.dat";

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Excluir Avaliação");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField idField = new TextField();
        idField.setPromptText("ID da Avaliação");

        Button btnExcluir = new Button("Excluir Avaliação");
        btnExcluir.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText().trim());

                boolean removido = AvaliarProdutoDAO.excluirAvaliacao(id, CAMINHO_ARQUIVO);
                if (removido) {
                    mostrarAlerta("Avaliação excluída com sucesso.", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Avaliação não encontrada.", Alert.AlertType.WARNING);
                }

                idField.clear();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro: ID deve ser numérico.", Alert.AlertType.WARNING);
            } catch (Exception ex) {
                mostrarAlerta("Erro ao excluir avaliação: " + ex.getMessage(), Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

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
                btnExcluir,
                btnVoltar
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Excluir Avaliação");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
