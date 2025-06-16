package br.pucpr.omcejavafx.Pagamento;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javafx.application.Application.launch;

public class PaginaAtualizarPagamento extends Application {

    private TextField idField;
    private TextField metodoPagamento;
    private DatePicker Data;


    private Pagamento pagamentoAtual;

    @Override
    public void start(Stage stage) {
        Label idLabel = new Label("ID do Pagamento:");
        idField = new TextField();
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudPagamento menu = new PaginaEscolherCrudPagamento();
            menu.voltarMenuPagamento(stage);
        });
        Button buscarButton = new Button("Buscar");
        buscarButton.setOnAction(e -> buscarPagamento());

        metodoPagamento = new TextField();
        Data = new DatePicker();

        Button salvarButton = new Button("Salvar Alterações");
        salvarButton.setOnAction(e -> salvarAlteracoes());

        VBox layout = new VBox(10, idLabel, idField, buscarButton,
                new Label("Metodo de Pagamento:"), metodoPagamento,
                new Label("Data:"), Data,voltarBtn,
                salvarButton);

        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 500);
        stage.setTitle("Editar Pagamento");
        stage.setScene(scene);
        stage.show();
    }

    private void buscarPagamento() {
        try {
            int id = parseInt(idField.getText());
            List<Pagamento> pagamentos = PagamentoDAO.listar();

            for (Pagamento p : pagamentos) {
                if (p.getId() == id) {
                    pagamentoAtual = p;
                    preencherFormulario(p);
                    return;
                }
            }

            mostrarAlerta("Pagamento não encontrado", Alert.AlertType.WARNING);

        } catch (NumberFormatException ex) {
            mostrarAlerta("ID inválido", Alert.AlertType.ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void preencherFormulario(Pagamento p) {
        metodoPagamento.setText(p.getMetodoPagamento());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data =  LocalDate.parse(p.getData(), formatter);
        Data.setValue(data);


    }

    private void salvarAlteracoes() {
        if (pagamentoAtual == null) {
            mostrarAlerta("Nenhum produto carregado.", Alert.AlertType.ERROR);
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = Data.getValue().format(formatter);
        try {
            pagamentoAtual.setMetodoPagamento(metodoPagamento.getText());
            pagamentoAtual.setData(dataFormatada);
            PagamentoDAO.atualizar(pagamentoAtual);

            mostrarAlerta("Pagamento atualizado com sucesso!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException | IOException e) {
            mostrarAlerta("Erro ao salvar: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
