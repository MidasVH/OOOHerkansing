package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CashRegisterMainPane extends BorderPane {
    public CashRegisterMainPane(Pane articleOverviewPane, Pane cashRegisterPane){
        TabPane tabPane = new TabPane();

        Tab cashregisterTab = new Tab("Cash Register", cashRegisterPane);
        Tab articleOverviewTab = new Tab("Articles", articleOverviewPane);
        Tab settingsTab = new Tab("Settings");
        Tab logTab = new Tab("Log");

        cashregisterTab.setClosable(false);
        articleOverviewTab.setClosable(false);
        settingsTab.setClosable(false);
        logTab.setClosable(false);

        tabPane.getTabs().addAll(cashregisterTab, articleOverviewTab, settingsTab, logTab);
        this.setCenter(tabPane);
    }
}
