module net.alkitmessenger.alkitmessenger {

    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires com.google.gson;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires mysql.connector.java;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.alkitmessenger to javafx.fxml;

    exports net.alkitmessenger;

}