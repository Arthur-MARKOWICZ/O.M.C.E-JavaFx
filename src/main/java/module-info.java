module br.pucpr.omcejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens br.pucpr.omcejavafx to javafx.fxml;
    exports br.pucpr.omcejavafx;
    exports br.pucpr.omcejavafx.Pagamento;
    opens br.pucpr.omcejavafx.Pagamento to javafx.fxml;
    exports br.pucpr.omcejavafx.Pedido;
    opens br.pucpr.omcejavafx.Pedido to javafx.fxml;
    exports br.pucpr.omcejavafx.Usuario;
    opens br.pucpr.omcejavafx.Usuario to javafx.fxml;
    exports br.pucpr.omcejavafx.Produto;
    opens br.pucpr.omcejavafx.Produto to javafx.fxml;
    exports br.pucpr.omcejavafx.Avaliacao;
    opens br.pucpr.omcejavafx.Avaliacao to javafx.fxml;
}