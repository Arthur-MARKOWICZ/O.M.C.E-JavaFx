package br.pucpr.omcejavafx.Usuario;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PaginaListarUsuario extends Application {

    @Override
    public void start(Stage stage) {
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            PaginaEscolherCrudUsuario menu = new PaginaEscolherCrudUsuario();
            menu.voltarMenuUsuario(stage);
        });

        TableView<Usuario> tabela = new TableView<>();
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

        TableColumn<Usuario, String> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        TableColumn<Usuario, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome()));

        TableColumn<Usuario, String> usuarioColuna = new TableColumn<>("Usuário");
        usuarioColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNomeusuario()));

        TableColumn<Usuario, String> cpfColuna = new TableColumn<>("CPF");
        cpfColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCpf()));

        TableColumn<Usuario, String> sexoColuna = new TableColumn<>("Sexo");
        sexoColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSexo()));

        TableColumn<Usuario, String> nascimentoColuna = new TableColumn<>("Nascimento");
        nascimentoColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDatadenascimento()));

        TableColumn<Usuario, String> telefoneColuna = new TableColumn<>("Telefone");
        telefoneColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTelefone()));

        TableColumn<Usuario, String> emailColuna = new TableColumn<>("E-mail");
        emailColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<Usuario, String> ativoColuna = new TableColumn<>("Ativo");
        ativoColuna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAtivo() ? "Sim" : "Não"));

        tabela.getColumns().addAll(idColuna, nomeColuna, usuarioColuna, cpfColuna, sexoColuna,
                nascimentoColuna, telefoneColuna, emailColuna, ativoColuna);

        try {
            List<Usuario> lista = UsuarioSalvar.carregarUsuarios("usuario.dat");
            usuarios.addAll(lista);
            tabela.setItems(usuarios);
        } catch (Exception e) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao carregar usuários");
            erro.setHeaderText(null);
            erro.setContentText("Erro: " + e.getMessage());
            erro.showAndWait();
        }

        BorderPane layout = new BorderPane();
        VBox topo = new VBox(10, voltarBtn);
        topo.setStyle("-fx-padding: 10; -fx-alignment: center-left;");
        layout.setTop(topo);
        layout.setCenter(tabela);

        Scene scene = new Scene(layout, 800, 600);
        stage.setTitle("Lista de Usuarios Cadastrados");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
