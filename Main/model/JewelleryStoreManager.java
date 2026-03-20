package model;

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
        DisplayTray tray = new DisplayTray(trayId, inlayColor, widthCm, depthCm);
        dc.addTray(tray);
        return tray;
    }

    public JewelleryItem addItemToTray(String trayId, String description, String type,
                                       String targetGender, String imageUrl, double price) {
        DisplayTray tray = findTrayById(trayId);
        if (tray == null) return null;
        JewelleryItem item = new JewelleryItem(description, type, targetGender, imageUrl, price);
        tray.addItem(item);
        return item;
    }

    public void addMaterialToItem(JewelleryItem item, String name, String desc,
                                  double qty, double quality) {
        item.addMaterial(new Material(name, desc, qty, quality));
    }

    public DisplayCaseList getDisplayCases() {
        return displayCases;
    }