package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.DisplayCase;
import model.JewelleryStoreManager;
import structure.Node;

public class MainViewController {

    private final JewelleryStoreManager storeManager = JewelleryStoreApp.getStoreManager();

    @FXML
    private ListView<String> casesListView;

    @FXML
    private Button addCaseButton;

    @FXML
    private void initialize() {
        refreshCasesList();
    }

    @FXML
    private void onAddCaseClicked() {
        storeManager.addDisplayCase("wall-mounted", true);
        refreshCasesList();
    }

    private void refreshCasesList() {
        casesListView.getItems().clear();

        if (storeManager.getDisplayCases() == null) {
            return;
        }

        Node<DisplayCase> node = storeManager.getDisplayCases().getHead();
        while (node != null) {
            DisplayCase dc = node.data;
            casesListView.getItems().add("Case " + dc.getCaseId() + " (" + dc.getType() + ")");
            node = node.next;
        }
    }
}