package br.pucpr.omcejavafx.Pagamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VoltarPaginaPagamentoCrud {
    @FXML
    protected void onVoltar(ActionEvent event) throws IOException {

        FXMLLoader paginaEscolherCRUD = new FXMLLoader(
                PaginaEscolherCrudPagamento.class.getResource("escolherCrudPagamento-view.fxml")
        );

        Scene escolherclasse = new Scene(paginaEscolherCRUD.load(), 500, 500);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pagamento");
        stage.setScene(escolherclasse);
        stage.show();
    }
}
