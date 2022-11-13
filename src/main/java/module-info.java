module net.alkitmessenger.alkitmessenger {

    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;

    requires com.google.gson;
    requires org.hibernate.orm.core;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires mysql.connector.java;

    opens net.alkitmessenger to javafx.fxml;

    exports net.alkitmessenger;

}