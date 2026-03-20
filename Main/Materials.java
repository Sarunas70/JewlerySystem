// Material/component of jewellery
public class Material {
    private String name;          // gold, diamond
    private String description;   // cut, colour
    private double quantity;      // weight
    private double quality;       // karat, clarity 

    public Material(String name, String description, double quantity, double quality) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.quality = quality;
    }

    // Getters/setters...
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getQuality() {
        return quality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

}