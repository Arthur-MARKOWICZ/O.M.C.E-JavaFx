package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import java.util.List;

public class PaginaCadastroProduto extends Application {
    private File arquivoImagemSelecionado;
    private String imagemTipo;
    private byte[] imagem;
    @Override
    public void start(Stage stage) {
        VBox layout = new VBox();
        layout.setSpacing(10);

        Label nomeProduto = new Label("Nome do produto:");
        TextField nomeProdutoField = new TextField();
        VBox nomeProdutoBox = new VBox(10, nomeProduto, nomeProdutoField);

        Label preco = new Label("Preço:");
        TextField precoField = new TextField();
        VBox precoBox = new VBox(10, preco, precoField);

        Label detalhes = new Label("Detalhes:");
        TextField detalhesField = new TextField();
        VBox detalhesBox = new VBox(10, detalhes, detalhesField);

        Label condicaoLabel = new Label("Condição:");
        RadioButton rb1 = new RadioButton("NOVO");
        RadioButton rb2 = new RadioButton("USADO");
        ToggleGroup condicao = new ToggleGroup();
        rb1.setToggleGroup(condicao);
        rb2.setToggleGroup(condicao);

        Label categoriaLabel = new Label("Categoria:");
        ChoiceBox<String> categoria = new ChoiceBox<>(FXCollections.observableArrayList(
                "ESP-32", "ARDUINO", "RESISTORES", "SENSORES", "BATERIA", "CABOS",
                "MOTORES", "CONECTORES", "OUTROS"));

        Label imagemLabel = new Label("Imagem do Produto:");
        Button carregarImagemButton = new Button("Carregar Imagem");
        ImageView imagemView = new ImageView();
        imagemView.setFitWidth(200);
        imagemView.setPreserveRatio(true);

        carregarImagemButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Imagem do Produto");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );

            arquivoImagemSelecionado = fileChooser.showOpenDialog(stage);
            if (arquivoImagemSelecionado != null) {
                Image imagem = new Image(arquivoImagemSelecionado.toURI().toString());
                imagemView.setImage(imagem);

                // Obter o tipo da imagem
                String nomeArquivo = arquivoImagemSelecionado.getName();
                imagemTipo = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1).toLowerCase();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Nenhuma imagem selecionada");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, selecione uma imagem válida.");
                alerta.showAndWait();
            }
        });

        Button finalizarCadastro = new Button("Finalizar cadastro de produto");
        finalizarCadastro.setOnAction(actionEvent -> {
            try {
                // Validar campos obrigatórios
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

                // Converter a imagem para byte[]
                byte[] imagemBytes = Files.readAllBytes(arquivoImagemSelecionado.toPath());

                // Criar novo produto
                Produto produto = new Produto(
                        System.currentTimeMillis(), // ID único baseado no timestamp
                        nomeProdutoField.getText(),
                        Double.parseDouble(precoField.getText()),
                        detalhesField.getText(),
                        false, // vendido = false por padrão
                        imagem,
                        imagemTipo,
                        rb1.isSelected() ? "NOVO" : "USADO",
                        categoria.getValue()
                );

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Informação");
                info.setHeaderText("Local do arquivo");
                info.setContentText("Arquivo salvo em: " + ProdutoSalvar.getCaminhoArquivo());
                info.showAndWait();

                ProdutoSalvar.salvarProdutos((List<Produto>) produto, "produto.dat");

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setHeaderText(null);
                sucesso.setContentText("Produto cadastrado com sucesso!");
                sucesso.showAndWait();

                nomeProdutoField.clear();
                precoField.clear();
                detalhesField.clear();
                condicao.selectToggle(null);
                categoria.setValue(null);
                imagemView.setImage(null);
                arquivoImagemSelecionado = null;

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

        layout.getChildren().addAll(nomeProdutoBox, precoBox, detalhesBox, condicaoLabel, rb1, rb2,
                categoriaLabel, categoria, imagemLabel, carregarImagemButton, imagemView, finalizarCadastro);

        Scene scene = new Scene(layout, 400, 600);
        stage.setTitle("Cadastro de produto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}