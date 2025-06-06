package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaCadastrarUsuario extends Application {

    @Override
    public void start(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField nomeUsuarioField = new TextField();
        nomeUsuarioField.setPromptText("Nome de Usuário");

        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF");

        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("Senha");

        TextField sexoField = new TextField();
        sexoField.setPromptText("Sexo");

        TextField dataNascimentoField = new TextField();
        dataNascimentoField.setPromptText("Data de Nascimento");

        TextField telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");

        TextField cepField = new TextField();
        cepField.setPromptText("CEP");

        CheckBox ativoCheckBox = new CheckBox("Ativo");

        Button btnSalvar = new Button("Salvar Usuário");
        Label mensagem = new Label();

        btnSalvar.setOnAction(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                String nome = nomeField.getText();
                String nomeUsuario = nomeUsuarioField.getText();
                String cpf = cpfField.getText();
                String senha = senhaField.getText();
                String sexo = sexoField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();
                String cep = cepField.getText();
                boolean ativo = ativoCheckBox.isSelected();

                Usuario usuario = new Usuario(id, nome, nomeUsuario, cpf, senha,
                        sexo, dataNascimento, telefone, email, endereco, cep, ativo);

                UsuarioDAO.salvar(usuario);

                mensagem.setText("Usuário salvo com sucesso!");

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
                mensagem.setText("Erro: ID deve ser numérico.");
            } catch (Exception ex) {
                mensagem.setText("Erro ao salvar usuário: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10,
                new Label("Cadastro de Usuário"),
                idField,
                nomeField,
                nomeUsuarioField,
                cpfField,
                senhaField,
                sexoField,
                dataNascimentoField,
                telefoneField,
                emailField,
                enderecoField,
                cepField,
                ativoCheckBox,
                btnSalvar,
                mensagem
        );

        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 650);
        stage.setTitle("Inserir Usuário");
        stage.setScene(scene);
        stage.show();
    }
}
