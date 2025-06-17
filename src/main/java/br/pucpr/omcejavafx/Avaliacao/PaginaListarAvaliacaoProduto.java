package br.pucpr.omcejavafx.Avaliacao;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class PaginaListarAvaliacaoProduto extends Application {
    private static final String CAMINHO_ARQUIVO = "avaliacoes.dat";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public void start(Stage stage) {
        TableView<AvaliarProduto> tabela = new TableView<>();
        ObservableList<AvaliarProduto> avaliacoesObservable = FXCollections.observableArrayList();

        TableColumn<AvaliarProduto, String> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        TableColumn<AvaliarProduto, String> notaColuna = new TableColumn<>("Nota");
        notaColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("%.1f", cellData.getValue().getNota())));

        TableColumn<AvaliarProduto, String> comentarioColuna = new TableColumn<>("Comentário");
        comentarioColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getComentario()));

        TableColumn<AvaliarProduto, String> dataColuna = new TableColumn<>("Data da Avaliação");
        dataColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataAtual().format(formatter)));

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idColuna.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        notaColuna.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        comentarioColuna.setMaxWidth(1f * Integer.MAX_VALUE * 50);
        dataColuna.setMaxWidth(1f * Integer.MAX_VALUE * 30);

        tabela.getColumns().addAll(idColuna, notaColuna, comentarioColuna, dataColuna);

        try {
            List<AvaliarProduto> avaliacoes = AvaliarProdutoDAO.carregarAvaliacao(CAMINHO_ARQUIVO);
            avaliacoesObservable.addAll(avaliacoes);
            tabela.setItems(avaliacoesObservable);
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar avaliações:\n" + e.getMessage(), Alert.AlertType.ERROR);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new PaginaEscolherCrudAvaliacao().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox topo = new VBox(10, btnVoltar);
        topo.setStyle("-fx-padding: 10; -fx-alignment: center;");

        BorderPane layout = new BorderPane();
        layout.setTop(topo);
        layout.setCenter(tabela);

        Scene scene = new Scene(layout, 600, 400);
        stage.setTitle("Lista de Avaliações");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
