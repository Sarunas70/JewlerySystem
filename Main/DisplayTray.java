// Display tray that holds jewellery items
public class DisplayTray {
    private String trayId;    
    private String inlayColor;
    private double widthCm;
    private double depthCm;

    private JewelleryItemList items = new JewelleryItemList();

    public DisplayTray(String trayId, String inlayColor, double widthCm, double depthCm) {
        this.trayId = trayId;
        this.inlayColor = inlayColor;
        this.widthCm = widthCm;
        this.depthCm = depthCm;
    }

    public void addItem(JewelleryItem item) {
        items.add(item);
    }

    public JewelleryItemList getItems() {
        return items;
    }

    // Getters/setters...

    public String getTrayId() {
        return trayId;
    }

    public String getInlayColor() {
        return inlayColor;
    }

    public double getWidthCm() {
        return widthCm;
    }

    public double getDepthCm() {
        return depthCm;
    }

    public void setTrayId(String tray

Id) {
        this.trayId = trayId;
    }

    public void setInlayColor(String inlayColor) {
        this.inlayColor = inlayColor;
    }

    public void setWidthCm(double widthCm) {
        this.widthCm = widthCm;
    }

    public void setDepthCm(double depthCm) {
        this.depthCm = depthCm;
    }

}