package br.pucpr.omcejavafx.Usuario;
import br.pucpr.omcejavafx.PaginaEscolherCrudUsuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PaginaAtualizarUsuario extends Application {

    private TextField idField;
    private TextField nomeField;
    private TextField nomeUsuarioField;
    private TextField cpfField;
    private PasswordField senhaField;
    private ComboBox<String> sexoBox;
    private TextField dataNascimentoField;
    private TextField telefoneField;
    private TextField emailField;
    private TextField enderecoField;
    private TextField cepField;
    private CheckBox ativoCheckBox;

    private Usuario usuarioAtual;

    @Override
    public void start(Stage stage) {
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudUsuario menu = new PaginaEscolherCrudUsuario();
            menu.voltarMenuUsuario(stage);});
        Label idLabel = new Label("ID do Usuário:");
        idField = new TextField();

        Button buscarButton = new Button("Buscar");
        buscarButton.setOnAction(e -> buscarUsuario());

        nomeField = new TextField();
        nomeUsuarioField = new TextField();
        cpfField = new TextField();
        senhaField = new PasswordField();
        sexoBox = new ComboBox<>(FXCollections.observableArrayList("MASCULINO", "FEMININO", "OUTRO"));
        dataNascimentoField = new TextField();
        telefoneField = new TextField();
        emailField = new TextField();
        enderecoField = new TextField();
        cepField = new TextField();
        ativoCheckBox = new CheckBox("Ativo");

        Button salvarButton = new Button("Salvar Alterações");
        salvarButton.setOnAction(e -> salvarAlteracoes());

        VBox layout = new VBox(10, idLabel, idField, buscarButton,
                new Label("Nome:"), nomeField,
                new Label("Nome de Usuário:"), nomeUsuarioField,
                new Label("CPF:"), cpfField,
                new Label("Senha:"), senhaField,
                new Label("Sexo:"), sexoBox,
                new Label("Data de Nascimento:"), dataNascimentoField,
                new Label("Telefone:"), telefoneField,
                new Label("Email:"), emailField,
                new Label("Endereço:"), enderecoField,
                new Label("CEP:"), cepField,
                ativoCheckBox,
                salvarButton,voltarBtn);

        layout.setPadding(new Insets(10));
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 400, 600);
        stage.setTitle("Editar Usuário");
        stage.setScene(scene);
        stage.show();
    }

    private void buscarUsuario() {
        try {
            long id = Long.parseLong(idField.getText());
            List<Usuario> usuarios = UsuarioSalvar.carregarUsuarios("usuario.dat");
            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    usuarioAtual = u;
                    preencherFormulario(u);
                    return;
                }
            }

            mostrarAlerta("Usuário não encontrado", Alert.AlertType.WARNING);

        } catch (NumberFormatException ex) {
            mostrarAlerta("ID inválido", Alert.AlertType.ERROR);
        }
    }

    private void preencherFormulario(Usuario u) {
        nomeField.setText(u.getNome());
        nomeUsuarioField.setText(u.getNomeusuario());
        cpfField.setText(u.getCpf());
        senhaField.setText(u.getSenha());
        sexoBox.setValue(u.getSexo());
        dataNascimentoField.setText(u.getDatadenascimento());
        telefoneField.setText(u.getTelefone());
        emailField.setText(u.getEmail());
        enderecoField.setText(u.getEndereco());
        cepField.setText(u.getCep());
        ativoCheckBox.setSelected(u.getAtivo());
    }

    private void salvarAlteracoes() {
        if (usuarioAtual == null) {
            mostrarAlerta("Nenhum usuário carregado.", Alert.AlertType.ERROR);
            return;
        }

        try {
            usuarioAtual.setNome(nomeField.getText().trim());
            usuarioAtual.setNomeusuario(nomeUsuarioField.getText().trim());
            usuarioAtual.setCpf(cpfField.getText().trim());
            usuarioAtual.setSenha(senhaField.getText().trim());
            usuarioAtual.setSexo(sexoBox.getValue());
            usuarioAtual.setDatadenascimento(dataNascimentoField.getText().trim());
            usuarioAtual.setTelefone(telefoneField.getText().trim());
            usuarioAtual.setEmail(emailField.getText().trim());
            usuarioAtual.setEndereco(enderecoField.getText().trim());
            usuarioAtual.setCep(cepField.getText().trim());
            usuarioAtual.setAtivo(ativoCheckBox.isSelected());

            UsuarioSalvar.atualizarUsuario(usuarioAtual, "usuario.dat");

            mostrarAlerta("Usuário atualizado com sucesso!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException | IOException e) {
            mostrarAlerta("Erro ao salvar: " + e.getMessage(), Alert.AlertType.ERROR);
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
