package model;

import structure.DisplayCaseList;
import structure.Node;

public class JewelleryStoreManager {
    private DisplayCaseList displayCases = new DisplayCaseList();
    private int nextCaseId = 1;

    public DisplayCase addDisplayCase(String type, boolean lit) {
        DisplayCase dc = new DisplayCase(nextCaseId++, type, lit);
        displayCases.add(dc);
        return dc;
    }

    public DisplayTray addDisplayTray(int caseId, String trayId,
                                      String inlayColor, double widthCm, double depthCm) {
        if (findTrayById(trayId) != null) {
            return null;
        }
        DisplayCase dc = findCaseById(caseId);
        if (dc == null) return null;

        DisplayTray tray = new DisplayTray(trayId, widthCm, depthCm);
        dc.addTray(tray);
        return tray;
    }

    public JewelleryItem addItemToTray(String trayId, String description, String type,
                                       String targetGender,double price) {
        DisplayTray tray = findTrayById(trayId);
        if (tray == null) return null;

        JewelleryItem item = new JewelleryItem(description, type, targetGender, price);
        tray.addItem(item);
        return item;
    }


    public DisplayCaseList getDisplayCases() {
        return displayCases;
    }

    public DisplayCase findCaseById(int id) {
        Node<DisplayCase> current = displayCases.getHead();
        while (current != null) {
            if (current.data.getCaseId() == id) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public DisplayTray findTrayById(String trayId) {
        Node<DisplayCase> caseNode = displayCases.getHead();
        while (caseNode != null) {
            DisplayCase dc = caseNode.data;
            Node<DisplayTray> trayNode = dc.getTrays().getHead();
            while (trayNode != null) {
                if (trayNode.data.getTrayId().equalsIgnoreCase(trayId)) {
                    return trayNode.data;
                }
                trayNode = trayNode.next;
            }
            caseNode = caseNode.next;
        }
        return null;
    }

    public double calculateTotalStockValue() {
        double total = 0.0;
        Node<DisplayCase> caseNode = displayCases.getHead();
        while (caseNode != null) {
            Node<DisplayTray> trayNode = caseNode.data.getTrays().getHead();
            while (trayNode != null) {
                Node<JewelleryItem> itemNode = trayNode.data.getItems().getHead();
                while (itemNode != null) {
                    total += itemNode.data.getRetailPrice();
                    itemNode = itemNode.next;
                }
                trayNode = trayNode.next;
            }
            caseNode = caseNode.next;
        }
        return total;
    }

    public void reset() {
        displayCases = new DisplayCaseList();
        nextCaseId = 1;
    }
}