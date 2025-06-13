package br.pucpr.omcejavafx.Pagamento;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class PaginaCadastroPagamento extends Application {
    private TextField Id;
    private TextField metodoPagamento;
    private TextField Data;


    @Override
    public void start(Stage stage) {
        Label Idlabel = new Label("Id do Pagamento:");
        Id = new TextField();
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudPagamento menu = new PaginaEscolherCrudPagamento();
            menu.voltarMenuPagamento(stage);
        });
        Label metodoPagamentolabel = new Label("Metodo do Pagamento:");
        metodoPagamento = new TextField();

        Label Datalabel = new Label("Data do Pagamento:");
        Data = new TextField();







        Button finalizarCadastro = new Button("Finalizar cadastro de Pagamento");
        finalizarCadastro.setOnAction(actionEvent -> {
            try {
                if (Id.getText().isEmpty() || metodoPagamentolabel.getText().isEmpty()) {

                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Campos obrigatórios");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Por favor, preencha todos os campos obrigatórios.");
                    alerta.showAndWait();
                    return;
                }


                Pagamento pagamento = new Pagamento(
                        parseInt(Id.getText()),
                        metodoPagamentolabel.getText(),
                        Data.getText()
                );

                PagamentoDAO.salvar(pagamento);

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setHeaderText(null);
                sucesso.setContentText("Pagamento cadastrado com sucesso!");
                sucesso.showAndWait();
                limparFormulario();

            } catch (NumberFormatException e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro de formato");
                erro.setHeaderText(null);
                erro.showAndWait();
            } catch (IOException e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro ao salvar");
                erro.setHeaderText(null);
                erro.setContentText("Ocorreu um erro ao salvar o pagamento: " + e.getMessage());
                erro.showAndWait();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        VBox layout = new VBox(10, Idlabel, Id, metodoPagamentolabel, Datalabel,
                Data,voltarBtn,  finalizarCadastro);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 600);

        stage.setTitle("Cadastro de Pagamento");
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
