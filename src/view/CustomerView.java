package view;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerView {
    private Stage stage = new Stage();
    private Controller controller = Controller.getInstance();
    private CustomerViewPane customerViewPane;

    public CustomerView() {
        stage.setTitle("Customer View");
        stage.setResizable(false);
        stage.setX(775);
        stage.setY(20);


        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        customerViewPane = new CustomerViewPane();

        controller.getService().addObserver(customerViewPane);

        customerViewPane.prefWidthProperty().bind(scene.widthProperty());
        customerViewPane.prefHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(customerViewPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
