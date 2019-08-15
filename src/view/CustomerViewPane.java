package view;

import controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.domain.Observer;



public class CustomerViewPane extends GridPane implements Observer {
    private TableView table;
    private Controller controller = Controller.getInstance();
    private Label total;
    private ObservableMap<String, Integer> map;

    public CustomerViewPane(){
        setPadding(new Insets(5));
        this.setHgap(5);
        this.setVgap(5);

        table = new TableView();
        map = FXCollections.observableMap(controller.getShoppingCart());

        table.setPrefWidth(REMAINING);
        table.setItems(FXCollections.observableArrayList(controller.getArticleNumbers()));

        this.add(new Label("Scanned Articles"), 0, 0, 1, 1);

        TableColumn<String, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(cd -> Bindings.createStringBinding(() -> controller.getArticle(cd.getValue()).getDescription()));

        TableColumn<String, Integer> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(cd -> Bindings.valueAt(map, cd.getValue()));

        TableColumn<String, Double> articleTotalCol = new TableColumn<>("SubTotal");
        articleTotalCol.setCellValueFactory(cd -> Bindings.createObjectBinding(() -> controller.getArticleTotal(cd.getValue())));

        table.getColumns().addAll(descriptionCol, amountCol, articleTotalCol);
        this.add(table, 0, 1, 2, 4);

        this.add(new Label("Total Price: "), 0, 10, 1, 4);
        total = new Label(controller.getTotalCart() + " Euro");
        this.add(total, 1, 10, 1, 4);
    }

    @Override
    public void update(){
        table.setItems(FXCollections.observableArrayList(controller.getArticleNumbers()));
        table.refresh();
        total.setText(controller.getTotalCart() + " Euro");
    }
}
