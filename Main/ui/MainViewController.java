package ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DisplayCase;
import model.DisplayTray;
import model.JewelleryItem;
import model.JewelleryStoreManager;
import structure.Node;

import static java.rmi.server.LogStream.log;

public class MainViewController {
    private JewelleryStoreManager storeManager = JewelleryStoreApp.getStoreManager();

    @FXML
    private ListView<String> casesListView;
    @FXML
    private ListView<String> traysListView;
    @FXML
    private ListView<String> itemsListView;
    @FXML
    private TextArea logArea;

    // Case inputs
    @FXML
    private ComboBox<String> caseTypeCombo;
    @FXML
    private CheckBox caseLitCheck;

    // Tray input
    @FXML
    private TextField trayIdField;

    // Item inputs
    @FXML
    private TextField itemDescField;
    @FXML
    private TextField itemPriceField;

    @FXML
    private void initialize() {
        caseTypeCombo.setItems(FXCollections.observableArrayList(
                "wall-mounted", "freestanding"


        ));

        refreshCasesList();
        casesListView.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldVal, newVal) -> showTraysForSelectedCase()
        );
        traysListView.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldVal, newVal) -> showItemsForSelectedTray()
        );

        itemGenderCombo.setItems(FXCollections.observableArrayList(
                "male", "female", "unisex"
        ));

    }
//select gender
    @FXML private ComboBox<String> itemGenderCombo;



    // ===== ADD CASE =====
    @FXML
    private void onAddCaseClicked() {
        String type = caseTypeCombo.getValue();
        if (type == null) {
            showAlert("Select case type");
            return;
        }
        boolean lit = caseLitCheck.isSelected();

        storeManager.addDisplayCase(type, lit);
        refreshCasesList();
        log("Case added: " + type + " (" + (lit ? "lit" : "unlit") + ")");
        caseTypeCombo.setValue(null);
        caseLitCheck.setSelected(false);
    }

    // ===== ADD TRAY =====
    @FXML
    private void onAddTrayClicked() {
        int caseId = casesListView.getSelectionModel().getSelectedIndex() + 1;
        String trayId = trayIdField.getText().trim();

        if (caseId < 1) {
            showAlert("Select a case first (click it)");
            return;
        }
        if (trayId.isEmpty()) {
            showAlert("Enter tray ID");
            return;
        }

        DisplayTray tray = storeManager.addDisplayTray(caseId, trayId, "red", 30, 20);
        if (tray != null) {
            showTraysForSelectedCase();
            log("✅ Tray " + trayId + " added to case " + caseId);
            trayIdField.clear();
        } else {
            showAlert("Case not found or duplicate tray ID");
        }
    }

    private void showAlert(String s) {
    }

    // ===== ADD ITEM =====
    @FXML
    private void onAddItemClicked() {
        // Check tray selected
        int trayIndex = traysListView.getSelectionModel().getSelectedIndex();
        if (trayIndex < 0) {
            showAlert("Select a tray first (click it)");
            return;
        }

        // Get inputs
        String desc = itemDescField.getText().trim();
        String gender = itemGenderCombo.getValue();
        String priceText = itemPriceField.getText().trim();

        if (desc.isEmpty()) {
            showAlert("Enter item description");
            return;
        }
        if (gender == null) {
            showAlert("Select gender from dropdown");
            return;
        }
        if (priceText.isEmpty()) {
            showAlert("Enter price");
            return;
        }

        // Parse price
        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) {
                showAlert("Price must be positive");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Enter valid price (e.g. 1500.50)");
            return;
        }

        // Get selected tray ID
        String trayId = traysListView.getItems().get(trayIndex);

        // Add item
        JewelleryItem item = storeManager.addItemToTray(trayId, desc, "ring", gender, price);
        if (item != null) {
            showItemsForSelectedTray();
            log(desc + "' (" + gender + ") $" + price + " added to " + trayId);

            // Reset inputs
            itemDescField.clear();
            itemGenderCombo.setValue(null);
            itemPriceField.clear();
        } else {
            showAlert("Failed to add item");
        }
    }



    // ===== VIEW ALL =====
    @FXML
    private void onViewAllClicked() {
        StringBuilder sb = new StringBuilder("=== STOCK REPORT ===\n");
        sb.append("Total value: €").append(storeManager.calculateTotalStockValue()).append("\n\n");

        Node<DisplayCase> caseNode = storeManager.getDisplayCases().getHead();
        while (caseNode != null) {
            DisplayCase dc = caseNode.data;
            sb.append("Case ").append(dc.getCaseId()).append(" (").append(dc.getType()).append(")\n");
            caseNode = caseNode.next;
        }
        logArea.setText(sb.toString());
    }

    // ===== RESET =====
    @FXML
    private void onResetClicked() {
        storeManager.reset();
        refreshCasesList();
        logArea.clear();
        log("Store reset");
    }

    // ===== HELPERS =====
    private void refreshCasesList() {
        casesListView.getItems().clear();
        Node<DisplayCase> node = storeManager.getDisplayCases().getHead();
        while (node != null) {
            DisplayCase dc = node.data;
            casesListView.getItems().add("Case " + dc.getCaseId());
            node = node.next;
        }
    }

    private void showTraysForSelectedCase() {
        traysListView.getItems().clear();
        itemsListView.getItems().clear();

        int caseIndex = casesListView.getSelectionModel().getSelectedIndex();
        if (caseIndex < 0) return;

        Node<DisplayCase> caseNode = storeManager.getDisplayCases().getHead();
        for (int i = 0; i < caseIndex && caseNode != null; i++) {
            caseNode = caseNode.next;
        }
        if (caseNode == null) return;

        Node<DisplayTray> trayNode = caseNode.data.getTrays().getHead();
        while (trayNode != null) {
            traysListView.getItems().add(trayNode.data.getTrayId());
            trayNode = trayNode.next;
        }
    }

    private void showItemsForSelectedTray() {
        itemsListView.getItems().clear();
        int caseIndex = casesListView.getSelectionModel().getSelectedIndex();
        int trayIndex = traysListView.getSelectionModel().getSelectedIndex();

        if (caseIndex < 0 || trayIndex < 0) return;

        // Navigate to the case
        Node<DisplayCase> caseNode = storeManager.getDisplayCases().getHead();
        for (int i = 0; i < caseIndex && caseNode != null; i++) {
            caseNode = caseNode.next;
        }
        if (caseNode == null) return;

        // Navigate to the tray
        Node<DisplayTray> trayNode = caseNode.data.getTrays().getHead();
        for (int i = 0; i < trayIndex && trayNode != null; i++) {
            trayNode = trayNode.next;
        }
        if (trayNode == null) return;

        // Show items for this tray
        Node<JewelleryItem> itemNode = trayNode.data.getItems().getHead();
        while (itemNode != null) {
            JewelleryItem item = itemNode.data;
            itemsListView.getItems().add(itemDescField.getText() + " ($" + item.getRetailPrice() + ")");
            itemNode = itemNode.next;
        }


    }


}