module net.alkitmessenger.alkitmessenger {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.alkitmessenger.alkitmessenger to javafx.fxml;
    exports net.alkitmessenger.alkitmessenger;
}