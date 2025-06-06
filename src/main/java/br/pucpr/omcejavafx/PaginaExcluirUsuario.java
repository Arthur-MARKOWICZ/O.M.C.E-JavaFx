
package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaExcluirUsuario extends Application {

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID do usuário a excluir");

        Button btnExcluir = new Button("Excluir");
        Label mensagem = new Label();

        btnExcluir.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());

                boolean removido = UsuarioDAO.excluir(id);
                if (removido) {
                    mensagem.setText("Usuário excluído com sucesso.");
                } else {
                    mensagem.setText("Usuário não encontrado.");
                }

                idField.clear();
            } catch (NumberFormatException ex) {
                mensagem.setText("ID deve ser numérico.");
            } catch (Exception ex) {
                mensagem.setText("Erro: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10,
                new Label("Excluir Usuário"),
                idField,
                btnExcluir,
                mensagem
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 350, 250);
        stage.setTitle("Excluir Usuário");
        stage.setScene(scene);
        stage.show();
    }
}

