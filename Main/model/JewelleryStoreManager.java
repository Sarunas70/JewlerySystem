package model;

import structure.DisplayCaseList;
import structure.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JewelleryStoreManager {
    public DisplayCaseList displayCases = new DisplayCaseList();
    private int nextCaseId = 1;

    // ADD
    public DisplayCase addDisplayCase(String type, boolean lit) {
        DisplayCase dc = new DisplayCase(nextCaseId++, type, lit);
        displayCases.add(dc);
        return dc;
    }

    public DisplayTray addDisplayTray(int caseId, String trayId, String inlayColor, double width, double depth) {
        if (findTrayById(trayId) != null) return null;  // duplicate ID

        DisplayCase dc = findCaseById(caseId);
        if (dc == null) return null;

        DisplayTray tray = new DisplayTray(trayId, width, depth);
        dc.getTrays().add(tray);
        return tray;
    }

    public JewelleryItem addItemToTray(String trayId, String description, String type,
                                       String targetGender, double price) {
        DisplayTray tray = findTrayById(trayId);
        if (tray == null) return null;

        JewelleryItem item = new JewelleryItem(description, type, targetGender, price);
        tray.getItems().add(item);
        return item;
    }

    // REMOVE
    public boolean removeCase(int caseId) {
        Node<DisplayCase> prev = null;
        Node<DisplayCase> curr = displayCases.head;

        while (curr != null) {
            if (curr.data.getCaseId() == caseId) {
                if (prev == null) {
                    displayCases.head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                displayCases.size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public boolean removeTray(int caseId, String trayId) {
        DisplayCase dc = findCaseById(caseId);
        if (dc == null) return false;

        Node<DisplayTray> prev = null;
        Node<DisplayTray> curr = dc.getTrays().head;

        while (curr != null) {
            if (curr.data.getTrayId().equalsIgnoreCase(trayId)) {
                if (prev == null) {
                    dc.getTrays().head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                dc.getTrays().size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public boolean removeItemFromTray(String trayId, int itemIndex) {
        DisplayTray tray = findTrayById(trayId);
        if (tray == null) return false;

        Node<JewelleryItem> prev = null;
        Node<JewelleryItem> curr = tray.getItems().head;
        int index = 0;

        while (curr != null && index < itemIndex) {
            prev = curr;
            curr = curr.next;
            index++;
        }

        if (curr == null) return false;

        if (prev == null) {
            tray.getItems().head = curr.next;
        } else {
            prev.next = curr.next;
        }
        tray.getItems().size--;
        return true;
    }


    public DisplayCase findCaseById(int caseId) {
        Node<DisplayCase> curr = displayCases.head;
        while (curr != null) {
            if (curr.data.getCaseId() == caseId) return curr.data;
            curr = curr.next;
        }
        return null;
    }

    public DisplayTray findTrayById(String trayId) {
        Node<DisplayCase> caseNode = displayCases.head;
        while (caseNode != null) {
            DisplayCase dc = caseNode.data;
            Node<DisplayTray> trayNode = dc.getTrays().head;
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
        double total = 0;
        Node<DisplayCase> caseNode = displayCases.head;
        while (caseNode != null) {
            Node<DisplayTray> trayNode = caseNode.data.getTrays().head;
            while (trayNode != null) {
                Node<JewelleryItem> itemNode = trayNode.data.getItems().head;
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

    public DisplayCaseList getDisplayCases() {
        return displayCases;
    }



}
