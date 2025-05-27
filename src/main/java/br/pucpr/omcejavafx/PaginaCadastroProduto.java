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

public class PaginaCadastroProduto extends Application {
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

            File arquivoSelecionado = fileChooser.showOpenDialog(stage);
            if (arquivoSelecionado != null) {
                Image imagem = new Image(arquivoSelecionado.toURI().toString());
                imagemView.setImage(imagem);
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
            String nome = nomeProdutoField.getText();
            String precoProduto = precoField.getText();
            String detalhesProduto = detalhesField.getText();
            String condicaoProduto = rb1.isSelected() ? "NOVO" : (rb2.isSelected() ? "USADO" : "Não especificado");
            String categoriaSelecionada = categoria.getValue();

            System.out.println("Cadastro completo:");
            System.out.println("Nome: " + nome);
            System.out.println("Preço: " + precoProduto);
            System.out.println("Detalhes: " + detalhesProduto);
            System.out.println("Condição: " + condicaoProduto);
            System.out.println("Categoria: " + categoriaSelecionada);
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