package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class PaginaListarProduto extends Application {

    @Override
    public void start(Stage stage) {
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudProduto menu = new PaginaEscolherCrudProduto();
            menu.voltarMenuProduto(stage);
        });

        TableView<Produto> tabela = new TableView<>();
        ObservableList<Produto> produtos = FXCollections.observableArrayList();

        TableColumn<Produto, String> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        TableColumn<Produto, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeProduto()));

        TableColumn<Produto, String> precoColuna = new TableColumn<>("Preço");
        precoColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty("R$ " + String.format("%.2f", cellData.getValue().getPreco())));

        TableColumn<Produto, String> condicaoColuna = new TableColumn<>("Condição");
        condicaoColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCondicao()));

        TableColumn<Produto, String> categoriaColuna = new TableColumn<>("Categoria");
        categoriaColuna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategoria()));

        TableColumn<Produto, ImageView> imagemColuna = new TableColumn<>("Imagem");
        imagemColuna.setCellValueFactory(cellData -> {
            byte[] imgBytes = cellData.getValue().getImagem();
            ImageView imgView = new ImageView(new Image(new ByteArrayInputStream(imgBytes)));
            imgView.setFitWidth(80);
            imgView.setFitHeight(80);
            imgView.setPreserveRatio(true);
            return new javafx.beans.property.SimpleObjectProperty<>(imgView);
        });

        tabela.getColumns().addAll(idColuna, nomeColuna, precoColuna, condicaoColuna, categoriaColuna, imagemColuna);

        try {
            List<Produto> lista = ProdutoSalvar.carregarProdutos("produto.dat");
            produtos.addAll(lista);
            tabela.setItems(produtos);
        } catch (Exception e) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao carregar produtos");
            erro.setHeaderText(null);
            erro.setContentText("Erro: " + e.getMessage());
            erro.showAndWait();
        }

        BorderPane layout = new BorderPane();

        VBox topo = new VBox(10, voltarBtn);
        topo.setStyle("-fx-padding: 10; -fx-alignment: center-left;");
        layout.setTop(topo);
        layout.setCenter(tabela);

        Scene scene = new Scene(layout, 800, 600);
        stage.setTitle("Lista de Produtos Cadastrados");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
