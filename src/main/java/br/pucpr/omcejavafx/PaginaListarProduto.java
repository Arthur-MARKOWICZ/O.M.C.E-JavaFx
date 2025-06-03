package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class PaginaListarProduto extends Application {

    @Override
    public void start(Stage stage) {
        List<Produto> produtos = ProdutoSalvar.carregarProdutos();

        TableView<Produto> tabelaProdutos = new TableView<>();

        TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, String> colunaDescricao = new TableColumn<>("Descrição");
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        TableColumn<Produto, Double> colunaPreco = new TableColumn<>("Preço (R$)");
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Produto, String> colunaCondicao = new TableColumn<>("Condição");

        TableColumn<Produto, ImageView> colunaImagem = new TableColumn<>("Imagem");
        colunaImagem.setCellValueFactory(data -> {
            Produto produto = data.getValue();
            if (produto.getImagem() != null) {
                Image imagem = new Image(new ByteArrayInputStream(produto.getImagem()));
                ImageView imagemView = new ImageView(imagem);
                imagemView.setFitWidth(100);
                imagemView.setPreserveRatio(true);
                return new javafx.beans.property.SimpleObjectProperty<>(imagemView);
            }
            return null;
        });

        tabelaProdutos.getColumns().addAll(colunaNome, colunaDescricao, colunaPreco, colunaImagem);

        ObservableList<Produto> listaProdutos = FXCollections.observableArrayList(produtos);
        tabelaProdutos.setItems(listaProdutos);

        VBox root = new VBox(tabelaProdutos);
        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setTitle("Listagem de Produtos");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}