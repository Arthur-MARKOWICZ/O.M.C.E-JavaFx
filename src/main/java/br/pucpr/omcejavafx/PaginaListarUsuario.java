package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PaginaListarUsuario extends Application {

    @Override
    public void start(Stage stage) {
        TextArea areaUsuarios = new TextArea();
        areaUsuarios.setEditable(false);

        Button btnCarregar = new Button("Carregar Usuários");

        btnCarregar.setOnAction(e -> {
            try {
                List<Usuario> usuarios = UsuarioDAO.carregarTodos();
                if (usuarios.isEmpty()) {
                    areaUsuarios.setText("Nenhum usuário cadastrado.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Usuario u : usuarios) {
                        sb.append("ID: ").append(u.getId()).append("\n")
                                .append("Nome: ").append(u.getNome()).append("\n")
                                .append("Nome de Usuário: ").append(u.getNomeusuario()).append("\n")
                                .append("CPF: ").append(u.getCpf()).append("\n")
                                .append("Senha: ").append(u.getSenha()).append("\n")
                                .append("Sexo: ").append(u.getSexo()).append("\n")
                                .append("Nascimento: ").append(u.getDatadenascimento()).append("\n")
                                .append("Telefone: ").append(u.getTelefone()).append("\n")
                                .append("Email: ").append(u.getEmail()).append("\n")
                                .append("Endereço: ").append(u.getEndereco()).append("\n")
                                .append("CEP: ").append(u.getCep()).append("\n")
                                .append("Ativo: ").append(u.getAtivo()).append("\n")
                                .append("-------------------------------\n");
                    }
                    areaUsuarios.setText(sb.toString());
                }
            } catch (IOException ex) {
                areaUsuarios.setText("Erro ao carregar usuários: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10, new Label("Lista de Usuários:"), btnCarregar, areaUsuarios);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 500, 500);
        stage.setTitle("Listar Usuários");
        stage.setScene(scene);
        stage.show();
    }
}

