package view;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CashRegisterView {
    private Stage stage = new Stage();
    private ArticleOverviewPane articleOverviewPane;
    private CashRegisterPane cashRegisterPane;
    private Controller controller = Controller.getInstance();

    public CashRegisterView(){
        articleOverviewPane = new ArticleOverviewPane();
        cashRegisterPane = new CashRegisterPane();



        stage.setTitle("Cash Register View");
        stage.setResizable(false);
        stage.setX(20);
        stage.setY(20);


        Group root = new Group();
        Scene scene = new Scene(root, 750, 500);
        BorderPane borderPane = new CashRegisterMainPane(articleOverviewPane, cashRegisterPane);

        controller.getService().addObserver(articleOverviewPane);
        controller.getService().addObserver(cashRegisterPane);

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
