package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

public class PaginaExcluirProduto extends Application {

    private static final String CAMINHO_ARQUIVO = "produto.dat";

    private TextField idInput;
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        Label label = new Label("ID do Produto para excluir:");
        idInput = new TextField();
        Button excluirBtn = new Button("Excluir Produto");
        statusLabel = new Label();

        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudProduto menu = new PaginaEscolherCrudProduto();
            menu.voltarMenuProduto(stage);
        });

        excluirBtn.setOnAction(e -> excluirProduto());

        VBox root = new VBox(10, label, idInput, excluirBtn, statusLabel, voltarBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        stage.setTitle("Excluir Produto");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }


    private void excluirProduto() {
        try {
            long idParaExcluir = Long.parseLong(idInput.getText().trim());
            List<Produto> produtos = ProdutoSalvar.carregarProdutos(CAMINHO_ARQUIVO);

            boolean removido = produtos.removeIf(prod -> prod.getId() == idParaExcluir);

            if (removido) {
                try (FileOutputStream fos = new FileOutputStream(CAMINHO_ARQUIVO);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(produtos);
                }

                statusLabel.setText("Produto excluído com sucesso!");
            } else {
                statusLabel.setText("Produto não encontrado.");
            }

        } catch (NumberFormatException ex) {
            statusLabel.setText("ID inválido.");
        } catch (Exception ex) {
            statusLabel.setText("Erro ao excluir: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
