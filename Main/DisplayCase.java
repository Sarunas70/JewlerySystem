// Display case that holds trays
public class DisplayCase {
    private int caseId;       // unique identifier
    private String type;      // wall-mounted or freestanding
    private boolean lit;      // lit or unlit

    private DisplayTrayList trays = new DisplayTrayList();

    public DisplayCase(int caseId, String type, boolean lit) {
        this.caseId = caseId;
        this.type = type;
        this.lit = lit;
    }

    public void addTray(DisplayTray tray) {
        trays.add(tray);
    }

    public DisplayTrayList getTrays() {
        return trays;
    }

    // Getters/setters...

    public int getCaseId() {
        return caseId;
    }

    public String getType() {
        return type;
    }

    public boolean isLit() {
        return lit;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    
}

