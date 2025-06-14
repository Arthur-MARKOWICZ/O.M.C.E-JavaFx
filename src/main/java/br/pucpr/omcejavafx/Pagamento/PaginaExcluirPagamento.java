package br.pucpr.omcejavafx.Pagamento;

import br.pucpr.omcejavafx.Produto;
import br.pucpr.omcejavafx.ProdutoSalvar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PaginaExcluirPagamento  extends Application {

    private static final String CAMINHO_ARQUIVO = "pagamento.dat";

    private TextField idInput;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("ID do Pagamento para excluir:");
        idInput = new TextField();
        Button excluirBtn = new Button("Excluir Pagamento");
        statusLabel = new Label();
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudPagamento menu = new PaginaEscolherCrudPagamento();
            menu.voltarMenuPagamento(primaryStage);
        });
        excluirBtn.setOnAction(e -> excluirPagamento());

        VBox root = new VBox(10, label, idInput, excluirBtn, voltarBtn,statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setTitle("Excluir Pagamento");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void excluirPagamento() {
        try {
            long idParaExcluir = Long.parseLong(idInput.getText().trim());
            List<Pagamento> Pagamentos = PagamentoDAO.listar();

            boolean removido = Pagamentos.removeIf(pagamento -> pagamento.getId() == idParaExcluir);

            if (removido) {
                try (FileOutputStream fos = new FileOutputStream(CAMINHO_ARQUIVO);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(Pagamentos);
                }

                statusLabel.setText("Pagamento excluído com sucesso!");
            } else {
                statusLabel.setText("Pagamento não encontrado.");
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
