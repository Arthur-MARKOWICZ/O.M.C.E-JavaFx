module br.pucpr.omcejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens br.pucpr.omcejavafx to javafx.fxml;
    exports br.pucpr.omcejavafx;
}