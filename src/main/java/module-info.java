module br.pucpr.omcejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens br.pucpr.omcejavafx to javafx.fxml;
    exports br.pucpr.omcejavafx;
    exports br.pucpr.omcejavafx.Cruds;
    opens br.pucpr.omcejavafx.Cruds to javafx.fxml;
    exports br.pucpr.omcejavafx.Pagamento;
    opens br.pucpr.omcejavafx.Pagamento to javafx.fxml;
}