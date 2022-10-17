module com.example.reversi {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    //requires org.testfx;

    opens com.reversi to javafx.fxml;
    exports com.reversi;
    opens com.reversi.Control to javafx.fxml;
    exports com.reversi.Control;
    opens com.reversi.Model.PlayableObjects to javafx.fxml;
    exports com.reversi.Model.PlayableObjects;
    opens com.reversi.Model.Utils to javafx.fxml;
    exports com.reversi.Model.Utils;
    opens com.reversi.Model to javafx.fxml;
    exports com.reversi.Model;
}