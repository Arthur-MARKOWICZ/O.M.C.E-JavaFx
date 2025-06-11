package br.pucpr.omcejavafx.Pagamento;

import br.pucpr.omcejavafx.Pagamento.Pagamento;
import br.pucpr.omcejavafx.Pagamento.PagamentoDAO;
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

import static java.lang.Integer.parseInt;

public class PaginaCadastroPagamento extends Application {
    private TextField Id;
    private TextField metodoPagamento;
    private TextField Data;


    @Override
    public void start(Stage stage) {
        Label nomeProdutoLabel = new Label("Id do Pagamento:");
        Id = new TextField();

        Label precoLabel = new Label("Metodo do Pagamento:");
        metodoPagamento = new TextField();

        Label detalhesLabel = new Label("Data do Pagamento:");
        Data = new TextField();







        Button finalizarCadastro = new Button("Finalizar cadastro de produto");
        finalizarCadastro.setOnAction(actionEvent -> {
            try {
                if (Id.getText().isEmpty() || metodoPagamento.getText().isEmpty()) {

                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Campos obrigatórios");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Por favor, preencha todos os campos obrigatórios e selecione uma imagem.");
                    alerta.showAndWait();
                    return;
                }


                Pagamento pagamento = new Pagamento(
                        parseInt(Id.getText()),
                        metodoPagamento.getText(),
                        Data.getText()
                );

                PagamentoDAO.salvar(pagamento);

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
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        VBox layout = new VBox(10, nomeProdutoLabel, Id, precoLabel, metodoPagamento, detalhesLabel,
                Data,  finalizarCadastro);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 600);

        stage.setTitle("Cadastro de Produto");
        stage.setScene(scene);
        stage.show();
    }

    private void limparFormulario() {
        Id.clear();
        metodoPagamento.clear();
        Data.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}
