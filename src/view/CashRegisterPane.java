package view;

import controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.domain.Article;
import model.domain.Observer;


public class CashRegisterPane extends GridPane implements Observer {
    private TableView table;
    private TextField inputField;
    private Label total, error;
    private Controller controller = Controller.getInstance();

    public CashRegisterPane(){
        table = new TableView();
        this.setPadding(new Insets(5));
        this.setVgap(5);
        this.setHgap(5);

        table.setPrefWidth(REMAINING);
        table.setItems(FXCollections.observableArrayList(controller.getArticlesInCart()));

        this.add(new Label("Enter an articlenumber: "), 0, 0, 1 ,1);
        inputField = new TextField();
        inputField.setPrefWidth(50);
        this.add(inputField, 1, 0, 1,1);
        inputField.setOnKeyPressed(new addItemHandler());

        error = new Label("This is not a valid articlenumber");
        error.setTextFill(Color.RED);
        error.setVisible(false);
        this.add(error, 2,0,1,1);

        this.add(new Label("Scanned Articles"), 0, 1,1,1);


        TableColumn<Article, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));

        TableColumn<Article, Double> priceCol = new TableColumn<>("Prijs");
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn<Article, Article> removeCol = new TableColumn<>("Remove");
        removeCol.setMinWidth(40);
        removeCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        removeCol.setCellFactory(param -> new TableCell<Article, Article>(){
            private final Button removeButton = new Button("Remove");

            @Override
            protected void updateItem(Article article, boolean empty){
                super.updateItem(article, empty);
                if(article == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(removeButton);
                removeButton.setOnAction(event -> controller.removeFromCart(article.getArticlenumber()));
            }
        });

        table.getColumns().addAll(descriptionCol, priceCol, removeCol);

        this.add(table, 0, 2, 3,4);

        this.add(new Label("Total: "), 0, 10, 1,1);
        total = new Label(controller.getTotalCart() + " Euro");
        this.add(total, 1,10,1,1);
    }

    @Override
    public void update(){
        table.setItems(FXCollections.observableArrayList(controller.getArticlesInCart()));
        total.setText(controller.getTotalCart() + " Euro");
    }

    class addItemHandler implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event){
            try{
                if(event.getCode() == KeyCode.ENTER){
                    error.setVisible(false);
                    String articlenumber = inputField.getText();
                    controller.addToCart(articlenumber);
                    inputField.clear();
                }
            }catch(IllegalArgumentException e){
                inputField.clear();
                error.setText(e.getMessage());
                error.setVisible(true);
            }
        }
    }
}
