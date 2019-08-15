package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.Article;
import model.domain.Observer;

public class ArticleOverviewPane extends GridPane implements Observer {
    private TableView table;
    private Controller controller = Controller.getInstance();

    public ArticleOverviewPane(){
        this.setPadding(new Insets(5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Articles"), 0, 0, 1, 1);
        table = new TableView();
        table.setItems(FXCollections.observableArrayList(controller.getArticles().values()));
        table.setPrefWidth(REMAINING);

        TableColumn<Article, String> descriptionCol = new TableColumn<>("Descritpion");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));

        TableColumn<Article, String> articlegroupCol = new TableColumn<>("Articlegroup");
        articlegroupCol.setCellValueFactory(new PropertyValueFactory("articlegroup"));

        TableColumn<Article, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn<Article, String> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory("stock"));

        table.getColumns().addAll(descriptionCol, articlegroupCol, priceCol, stockCol);
        this.add(table, 0, 1, 2,4);
    }

    @Override
    public void update(){
        table.setItems(FXCollections.observableArrayList(controller.getArticles().values()));
        table.refresh();
    }
}
