package br.pucpr.omcejavafx.Pagamento;

import br.pucpr.omcejavafx.MenuPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class PaginaEscolherCrudPagamento  extends Application {
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Button voltarBtn = new Button("Voltar");
        voltarBtn.setOnAction(e -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.voltarMenuPrincipal(stage);
        });
        Button btnInserir = new Button("Inserir Pagamento");
        Button btnConsultar = new Button("Consultar Pagamento");
        Button btnAtualizar = new Button("Atualizar Pagamento");
        Button btnExcluir = new Button("Excluir Pagamento");

        btnInserir.setOnAction(event -> abrirPaginaCadastro(stage));
        btnConsultar.setOnAction(event -> abrirPaginaConsulta(stage));
        btnAtualizar.setOnAction(event -> abrirPaginaAtualizacao(stage));
        btnExcluir.setOnAction(event -> abrirPaginaExclusao(stage));

        layout.getChildren().addAll(btnInserir, btnConsultar, btnAtualizar, btnExcluir, voltarBtn);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação - Pagamento");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaCadastro(Stage stage) {
        PaginaCadastroPagamento pagina = new PaginaCadastroPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaConsulta(Stage stage) {
        PaginaConsultaPagamento pagina = new PaginaConsultaPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaAtualizacao(Stage stage) {
        PaginaAtualizarPagamento pagina = new PaginaAtualizarPagamento();
        pagina.start(stage);
    }

    private void abrirPaginaExclusao(Stage stage) {
        PaginaExcluirPagamento pagina = new PaginaExcluirPagamento();
        pagina.start(stage);
    }
    public void voltarMenuPagamento(Stage stage){
        PaginaEscolherCrudPagamento pagina = new PaginaEscolherCrudPagamento();
        pagina.start(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}
