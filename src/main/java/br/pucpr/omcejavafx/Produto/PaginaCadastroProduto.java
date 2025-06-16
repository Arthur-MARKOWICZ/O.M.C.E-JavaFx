package br.pucpr.omcejavafx.Produto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PaginaCadastroProduto extends Application {
    private TextField nomeProdutoField;
    private TextField precoField;
    private TextArea detalhesField;
    private ToggleGroup condicao;
    private ComboBox<String> categoria;
    private File arquivoImagemSelecionado;
    private ImageView imagemView;

    @Override
    public void start(Stage stage) {

        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudProduto menu = new PaginaEscolherCrudProduto();
            menu.voltarMenuProduto(stage);
        });

        Label nomeProdutoLabel = new Label("Nome do Produto:");
        nomeProdutoField = new TextField();

        Label precoLabel = new Label("Preço:");
        precoField = new TextField();

        Label detalhesLabel = new Label("Detalhes:");
        detalhesField = new TextArea();

        Label condicaoLabel = new Label("Condição:");
        RadioButton rb1 = new RadioButton("Novo");
        RadioButton rb2 = new RadioButton("Usado");
        condicao = new ToggleGroup();
        rb1.setToggleGroup(condicao);
        rb2.setToggleGroup(condicao);

        Label categoriaLabel = new Label("Categoria:");
        categoria = new ComboBox<>(FXCollections.observableArrayList("ESP-32", "ARDUINO", "REGISTORES", "REGISTORES", "SENSORES", "BATERIA",
                "CABOS","MOTORES","CONECTORES","OUTROS"));
        categoria.setPromptText("Selecione uma categoria");

        Button selecionarImagemButton = new Button("Selecionar imagem");
        imagemView = new ImageView();
        imagemView.setFitWidth(150);
        imagemView.setFitHeight(150);
        imagemView.setPreserveRatio(true);

        selecionarImagemButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
            arquivoImagemSelecionado = fileChooser.showOpenDialog(stage);

            if (arquivoImagemSelecionado != null) {
                Image imagem = new Image(arquivoImagemSelecionado.toURI().toString());
                imagemView.setImage(imagem);
            }
        });

        Button finalizarCadastro = new Button("Finalizar cadastro de produto");
        finalizarCadastro.setOnAction(actionEvent -> {
            try {
                if (nomeProdutoField.getText().isEmpty() || precoField.getText().isEmpty() ||
                        condicao.getSelectedToggle() == null || categoria.getValue() == null ||
                        arquivoImagemSelecionado == null) {

                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Campos obrigatórios");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Por favor, preencha todos os campos obrigatórios e selecione uma imagem.");
                    alerta.showAndWait();
                    return;
                }
                byte[] imagemBytes = Files.readAllBytes(arquivoImagemSelecionado.toPath());
                String imagemTipo = Files.probeContentType(arquivoImagemSelecionado.toPath());

                Produto produto = new Produto(
                        System.currentTimeMillis(),
                        nomeProdutoField.getText(),
                        Double.parseDouble(precoField.getText()),
                        detalhesField.getText(),
                        false,
                        imagemBytes,
                        imagemTipo,
                        condicao.getSelectedToggle().toString().contains("Novo") ? "NOVO" : "USADO",
                        categoria.getValue()
                );

                ProdutoSalvar.salvarProduto(produto, "produto.dat");

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setHeaderText(null);
                sucesso.setContentText("Produto cadastrado com sucesso!");
                sucesso.showAndWait();
                limparFormulario();

            } catch (NumberFormatException e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro de formato");
                erro.setHeaderText(null);
                erro.setContentText("Por favor, insira um valor numérico válido para o preço.");
                erro.showAndWait();
            } catch (IOException e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro ao salvar");
                erro.setHeaderText(null);
                erro.setContentText("Ocorreu um erro ao salvar o produto: " + e.getMessage());
                erro.showAndWait();
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(10, nomeProdutoLabel, nomeProdutoField, precoLabel, precoField, detalhesLabel,
                detalhesField, condicaoLabel, rb1, rb2, categoriaLabel, categoria, selecionarImagemButton, imagemView, finalizarCadastro,voltarBtn);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 700);

        stage.setTitle("Cadastro de Produto");
        stage.setScene(scene);
        stage.show();
    }

    private void limparFormulario() {
        nomeProdutoField.clear();
        precoField.clear();
        detalhesField.clear();
        condicao.selectToggle(null);
        categoria.setValue(null);
        imagemView.setImage(null);
        arquivoImagemSelecionado = null;
    }

    public static void main(String[] args) {
        launch();
    }
}
