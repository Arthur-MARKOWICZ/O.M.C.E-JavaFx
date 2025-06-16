package br.pucpr.omcejavafx;

import br.pucpr.omcejavafx.Pagamento.PaginaEscolherCrudPagamento;
import br.pucpr.omcejavafx.Pedido.PaginaEscolherCrudPedido;
import br.pucpr.omcejavafx.Produto.PaginaEscolherCrudProduto;
import br.pucpr.omcejavafx.Usuario.PaginaEscolherCrudUsuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button btnUsuario = new Button("Usuario");
        Button btnProduto = new Button("Produto");
        Button btnPedido = new Button("Pedido");
        Button btnPagamento = new Button("Pagamento");
        Button btnAvaliacao = new Button("Avaliação Produto");

        btnUsuario.setOnAction(event -> abrirPaginaUsuario(stage));
        btnProduto.setOnAction(event -> abrirPaginaProduto(stage));
        btnPedido.setOnAction(event -> abrirPaginaPedido(stage));
        btnPagamento.setOnAction(event -> abrirPaginaPagamento(stage));
        btnAvaliacao.setOnAction(event -> abrirPaginaAvaliacao(stage));

        layout.getChildren().addAll(btnUsuario, btnProduto, btnPedido, btnPagamento, btnAvaliacao);

        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Escolher Ação");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPaginaUsuario(Stage stage) {
       PaginaEscolherCrudUsuario pagina = new PaginaEscolherCrudUsuario();
        pagina.start(stage);
    }
    private void abrirPaginaProduto(Stage stage) {
        PaginaEscolherCrudProduto pagina = new PaginaEscolherCrudProduto();
        pagina.start(stage);
    }
    private void abrirPaginaPedido(Stage stage) {
        PaginaEscolherCrudPedido pagina = new PaginaEscolherCrudPedido();
        pagina.start(stage);
    }
    private void abrirPaginaPagamento(Stage stage) {
        PaginaEscolherCrudPagamento pagina = new PaginaEscolherCrudPagamento();
        pagina.start(stage);
    }
    // Arrumar para avaliação produto
    private void abrirPaginaAvaliacao(Stage stage) {
        PaginaEscolherCrudAvaliacao pagina = new PaginaEscolherCrudAvaliacao();
        //pagina.start(stage);
    }
    public void voltarMenuPrincipal(Stage stage){
        MenuPrincipal pagina = new MenuPrincipal();
        pagina.start(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}
