package ui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainViewController {

    private JewelleryStoreManager storeManager = JewelleryStoreApp.getStoreManager();

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
        // TODO: show dialog to enter type & lit, then:
        // storeManager.addDisplayCase("wall-mounted", true);
        refreshCasesList();
    }

    private void refreshCasesList() {
        casesListView.getItems().clear();
        Node<DisplayCase> node = storeManager.getDisplayCases().getHead();
        while (node != null) {
            DisplayCase dc = node.data;
            casesListView.getItems().add("Case " + dc.getCaseId() + " (" + dc.getType() + ")");
            node = node.next;
        }
    }
}
