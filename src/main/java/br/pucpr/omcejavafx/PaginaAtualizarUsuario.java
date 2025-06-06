package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaAtualizarUsuario extends Application {

    @Override
    public void start(Stage stage) {
        // Campos de entrada
        TextField idField = new TextField(); idField.setPromptText("ID");
        TextField nomeField = new TextField(); nomeField.setPromptText("Nome");
        TextField nomeUsuarioField = new TextField(); nomeUsuarioField.setPromptText("Nome de Usuário");
        TextField cpfField = new TextField(); cpfField.setPromptText("CPF");
        PasswordField senhaField = new PasswordField(); senhaField.setPromptText("Senha");
        TextField sexoField = new TextField(); sexoField.setPromptText("Sexo");
        TextField dataNascimentoField = new TextField(); dataNascimentoField.setPromptText("Data de Nascimento");
        TextField telefoneField = new TextField(); telefoneField.setPromptText("Telefone");
        TextField emailField = new TextField(); emailField.setPromptText("Email");
        TextField enderecoField = new TextField(); enderecoField.setPromptText("Endereço");
        TextField cepField = new TextField(); cepField.setPromptText("CEP");
        CheckBox ativoCheckBox = new CheckBox("Ativo");

        Button btnAtualizar = new Button("Atualizar");
        Label mensagem = new Label();

        btnAtualizar.setOnAction(e -> {
            try {
                Usuario usuario = new Usuario();

                usuario.setId(Long.parseLong(idField.getText().trim()));
                usuario.setNome(nomeField.getText().trim());
                usuario.setNomeusuario(nomeUsuarioField.getText().trim());
                usuario.setCpf(cpfField.getText().trim());
                usuario.setSenha(senhaField.getText().trim());
                usuario.setSexo(sexoField.getText().trim());
                usuario.setDatadenascimento(dataNascimentoField.getText().trim());
                usuario.setTelefone(telefoneField.getText().trim());
                usuario.setEmail(emailField.getText().trim());
                usuario.setEndereco(enderecoField.getText().trim());
                usuario.setCep(cepField.getText().trim());
                usuario.setAtivo(ativoCheckBox.isSelected());

                // Chamada ao DAO (supondo que este método exista)
                boolean atualizado = UsuarioDAO.atualizar(usuario);

                if (atualizado) {
                    mensagem.setText("Usuário atualizado com sucesso.");
                } else {
                    mensagem.setText("Usuário não encontrado.");
                }

                // Limpar campos
                idField.clear();
                nomeField.clear();
                nomeUsuarioField.clear();
                cpfField.clear();
                senhaField.clear();
                sexoField.clear();
                dataNascimentoField.clear();
                telefoneField.clear();
                emailField.clear();
                enderecoField.clear();
                cepField.clear();
                ativoCheckBox.setSelected(false);

            } catch (NumberFormatException ex) {
                mensagem.setText("ID deve ser um número.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao atualizar: " + ex.getMessage());
                ex.printStackTrace(); // Para debug
            }
        });

        VBox layout = new VBox(8,
                new Label("Atualizar Usuário"),
                idField, nomeField, nomeUsuarioField, cpfField, senhaField, sexoField,
                dataNascimentoField, telefoneField, emailField, enderecoField, cepField,
                ativoCheckBox, btnAtualizar, mensagem
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 650);
        stage.setTitle("Atualizar Usuário");
        stage.setScene(scene);
        stage.show();
    }
}

