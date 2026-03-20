package model;

import structure.JewelleryItemList;

// Display tray that holds jewellery items
public class DisplayTray {
    private String trayId;
    private double widthCm;
    private double depthCm;

    private JewelleryItemList items = new JewelleryItemList();

    public DisplayTray(String trayId, double widthCm, double depthCm) {
        this.trayId = trayId;

        this.widthCm = widthCm;
        this.depthCm = depthCm;
    }

    public void addItem(JewelleryItem item) {
        items.add(item);
    }

    public JewelleryItemList getItems() {
        return items;
    }

    public String getTrayId() {
        return trayId;
    }



    public double getWidthCm() {
        return widthCm;
    }

    public double getDepthCm() {
        return depthCm;
    }

    public void setTrayId(String trayId) {
        this.trayId = trayId;
    }



    public void setWidthCm(double widthCm) {
        this.widthCm = widthCm;
    }

    public void setDepthCm(double depthCm) {
        this.depthCm = depthCm;
    }
}