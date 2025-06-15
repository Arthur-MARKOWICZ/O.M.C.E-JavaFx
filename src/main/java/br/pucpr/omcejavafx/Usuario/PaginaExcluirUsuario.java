package br.pucpr.omcejavafx.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

public class PaginaExcluirUsuario extends Application {

    private static final String CAMINHO_ARQUIVO = "usuario.dat";

    private TextField idInput;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("ID do Usuario para excluir:");
        idInput = new TextField();
        Button excluirBtn = new Button("Excluir Usuario");
        statusLabel = new Label();

        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudUsuario menu = new PaginaEscolherCrudUsuario();
            menu.voltarMenuUsuario(primaryStage);});



        excluirBtn.setOnAction(e -> excluirUsuario());

        VBox root = new VBox(10, label, idInput, excluirBtn, statusLabel, voltarBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setTitle("Excluir Usuario");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void excluirUsuario() {
        try {
            long idParaExcluir = Long.parseLong(idInput.getText().trim());
            List<Usuario> usuarios = UsuarioSalvar.carregarUsuarios(CAMINHO_ARQUIVO);

            boolean removido = usuarios.removeIf(usuario -> usuario.getId() == idParaExcluir);

            if (removido) {
                try (FileOutputStream fos = new FileOutputStream(CAMINHO_ARQUIVO);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(usuarios);
                }

                statusLabel.setText("Usuario excluído com sucesso!");
            } else {
                statusLabel.setText(" Usuario não encontrado.");
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
