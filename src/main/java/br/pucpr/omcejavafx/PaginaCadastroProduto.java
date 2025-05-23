package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaCadastroProduto extends Application {
    @Override
    public void start(Stage stage) {
     
        VBox layout = new VBox();
        layout.setSpacing(10);

        Label nomeProduto = new Label("Nome do produto:");
        TextField nomeProdutoField = new TextField();
        VBox nomeProdutoBox = new VBox();
        nomeProdutoBox.setSpacing(10);
        nomeProdutoBox.getChildren().addAll(nomeProduto, nomeProdutoField);

        Label preco = new Label("Preço:");
        TextField precoField = new TextField();
        VBox precoBox = new VBox();
        precoBox.setSpacing(10);
        precoBox.getChildren().addAll(preco, precoField);

        Label detalhes = new Label("Detalhes:");
        TextField detalhesField = new TextField();
        VBox detalhesBox = new VBox();
        detalhesBox.setSpacing(20);
        detalhesBox.getChildren().addAll(detalhes, detalhesField);


        Label Condicao = new Label("Condição:");
        RadioButton rb1 = new RadioButton("NOVO");
        RadioButton rb2 = new RadioButton("USADO");


        ToggleGroup condicao = new ToggleGroup();
        rb1.setToggleGroup(condicao);
        rb2.setToggleGroup(condicao);

        Label Categoria = new Label("Categoria:");
        ChoiceBox categoria = new ChoiceBox(FXCollections.observableArrayList(
                "ESP-32", "ARDUINO", "REGISTORES", "REGISTORES", "SENSORES", "BATERIA",
                "CABOS","MOTORES","CONECTORES","OUTROS")
        );

        Button finalizarCadastro = new Button("Finalizar cadastro de produto");


        layout.getChildren().addAll(nomeProdutoBox,precoBox,detalhesBox, Condicao,
                rb1, rb2,Categoria,categoria,finalizarCadastro);


        Scene scene = new Scene(layout, 320, 240);

        stage.setTitle("Cadastro de produto");
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}