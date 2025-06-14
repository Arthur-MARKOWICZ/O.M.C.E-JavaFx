package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PaginaAtualizarProduto extends Application {

    private TextField idField;
    private TextField nomeProdutoField;
    private TextField precoField;
    private TextArea detalhesField;
    private ComboBox<String> condicaoBox;
    private ComboBox<String> categoriaBox;

    private Produto produtoAtual;

    @Override
    public void start(Stage stage) {
        Label idLabel = new Label("ID do Produto:");
        idField = new TextField();

        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudProduto menu = new PaginaEscolherCrudProduto();
            menu.voltarMenuProduto(stage);
        });

        Button buscarButton = new Button("Buscar");
        buscarButton.setOnAction(e -> buscarProduto());

        nomeProdutoField = new TextField();
        precoField = new TextField();
        detalhesField = new TextArea();

        condicaoBox = new ComboBox<>(FXCollections.observableArrayList("NOVO", "USADO"));
        categoriaBox = new ComboBox<>(FXCollections.observableArrayList(
                "ESP-32", "ARDUINO", "REGISTORES", "SENSORES", "BATERIA",
                "CABOS", "MOTORES", "CONECTORES", "OUTROS"));

        Button salvarButton = new Button("Salvar Alterações");
        salvarButton.setOnAction(e -> salvarAlteracoes());

        VBox layout = new VBox(10, idLabel, idField, buscarButton,
                new Label("Nome:"), nomeProdutoField,
                new Label("Preço:"), precoField,
                new Label("Detalhes:"), detalhesField,
                new Label("Condição:"), condicaoBox,
                new Label("Categoria:"), categoriaBox,
                salvarButton, voltarBtn);

        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 500);
        stage.setTitle("Editar Produto");
        stage.setScene(scene);
        stage.show();
    }

    private void buscarProduto() {
        try {
            long id = Long.parseLong(idField.getText());
            List<Produto> produtos = ProdutoSalvar.carregarProdutos("produto.dat");

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    produtoAtual = p;
                    preencherFormulario(p);
                    return;
                }
            }

            mostrarAlerta("Produto não encontrado", Alert.AlertType.WARNING);

        } catch (NumberFormatException ex) {
            mostrarAlerta("ID inválido", Alert.AlertType.ERROR);
        }
    }

    private void preencherFormulario(Produto p) {
        nomeProdutoField.setText(p.getNomeProduto());
        precoField.setText(String.valueOf(p.getPreco()));
        detalhesField.setText(p.getDetalhes());
        condicaoBox.setValue(p.getCondicao());
        categoriaBox.setValue(p.getCategoria());
    }

    private void salvarAlteracoes() {
        if (produtoAtual == null) {
            mostrarAlerta("Nenhum produto carregado.", Alert.AlertType.ERROR);
            return;
        }

        try {
            produtoAtual.setNomeProduto(nomeProdutoField.getText());
            produtoAtual.setPreco(Double.parseDouble(precoField.getText()));
            produtoAtual.setDetalhes(detalhesField.getText());
            produtoAtual.setCondicao(condicaoBox.getValue());
            produtoAtual.setCategoria(categoriaBox.getValue());

            ProdutoSalvar.atualizarProduto(produtoAtual, "produto.dat");

            mostrarAlerta("Produto atualizado com sucesso!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException | IOException e) {
            mostrarAlerta("Erro ao salvar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
