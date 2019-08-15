package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArticleDatabase;
import model.db.ArticleDatabaseLocal;
import view.CashRegisterView;
import view.CustomerView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        CashRegisterView crv = new CashRegisterView();
        CustomerView cv = new CustomerView();
    }

    public static void main(String[] args){
        launch(args);
    }
}
