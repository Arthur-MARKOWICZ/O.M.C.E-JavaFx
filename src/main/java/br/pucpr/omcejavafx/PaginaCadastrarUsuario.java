package br.pucpr.omcejavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaCadastrarUsuario extends Application {
    private TextField idField;
    private TextField nomeField;
    private TextField nomeUsuarioField;
    private TextField cpfField;
    private PasswordField senhaField;
    private ToggleGroup sexoField;
    private TextField dataNascimentoField;
    private TextField telefoneField;
    private TextField emailField;
    private TextField enderecoField;
    private TextField cepField;
    private CheckBox ativoCheckBox;

    @Override
    public void start(Stage stage) {
        idField = new TextField();
        idField.setPromptText("ID");

        nomeField = new TextField();
        nomeField.setPromptText("Nome");

        nomeUsuarioField = new TextField();
        nomeUsuarioField.setPromptText("Nome de Usuário");

        cpfField = new TextField();
        cpfField.setPromptText("CPF");

        senhaField = new PasswordField();
        senhaField.setPromptText("Senha");

        Label sexoLabel = new Label("Sexo:");
        RadioButton rb1 = new RadioButton("Masculino");
        RadioButton rb2 = new RadioButton("Feminino");
        RadioButton rb3 = new RadioButton("Prefiro não me identificar");
        sexoField = new ToggleGroup();
        rb1.setToggleGroup(sexoField);
        rb2.setToggleGroup(sexoField);
        rb3.setToggleGroup(sexoField);

        VBox sexoBox = new VBox(5, sexoLabel, rb1, rb2, rb3);

        dataNascimentoField = new TextField();
        dataNascimentoField.setPromptText("Data de Nascimento");

        telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");

        emailField = new TextField();
        emailField.setPromptText("E-mail");

        enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");

        cepField = new TextField();
        cepField.setPromptText("CEP");

        ativoCheckBox = new CheckBox("Usuário Ativo");

        Button btnSalvar = new Button("Finalizar Cadastro de Usuário");
        btnSalvar.setOnAction(e -> salvarUsuario());

        VBox layout = new VBox(10.0,
                new Label("Cadastro de Usuário"),
                idField,
                nomeField,
                nomeUsuarioField,
                cpfField,
                senhaField,
                sexoBox,
                dataNascimentoField,
                telefoneField,
                emailField,
                enderecoField,
                cepField,
                ativoCheckBox,
                btnSalvar
        );

        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 650);

        stage.setTitle("Cadastro de Usuário");
        stage.setScene(scene);
        stage.show();
    }

    private void salvarUsuario() {
        try {
            if (idField.getText().isEmpty() || nomeField.getText().isEmpty() ||
                    nomeUsuarioField.getText().isEmpty() || cpfField.getText().isEmpty() ||
                    senhaField.getText().isEmpty()) {

                mostrarAlerta("Preencha os campos obrigatórios: ID, Nome, Nome de Usuário, CPF e Senha.", Alert.AlertType.WARNING);
                return;
            }

            long id = Long.parseLong(idField.getText());
            String nome = nomeField.getText();
            String nomeUsuario = nomeUsuarioField.getText();
            String cpf = cpfField.getText();
            String senha = senhaField.getText();

            Toggle selectedToggle = sexoField.getSelectedToggle();
            String sexo = "";
            if (selectedToggle != null && selectedToggle instanceof RadioButton) {
                sexo = ((RadioButton) selectedToggle).getText();
            }

            String dataNascimento = dataNascimentoField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();
            String endereco = enderecoField.getText();
            String cep = cepField.getText();
            boolean ativo = ativoCheckBox.isSelected();

            Usuario usuario = new Usuario(id, nome, nomeUsuario, cpf, senha,
                    sexo, dataNascimento, telefone, email, endereco, cep, ativo);

            UsuarioSalvar.salvarUsuario(usuario, "usuario.dat");

            mostrarAlerta("Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            limparFormulario();
        } catch (NumberFormatException ex) {
            mostrarAlerta("O campo ID deve conter apenas números.", Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mostrarAlerta("Erro ao salvar usuário: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void limparFormulario() {
        idField.clear();
        nomeField.clear();
        nomeUsuarioField.clear();
        cpfField.clear();
        senhaField.clear();
        sexoField.selectToggle(null);
        dataNascimentoField.clear();
        telefoneField.clear();
        emailField.clear();
        enderecoField.clear();
        cepField.clear();
        ativoCheckBox.setSelected(false);
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

