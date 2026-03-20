package model;

import structure.MaterialList;


public class JewelleryItem {
    private String description;
    private String type;          // ring, watch, necklace
    private String targetGender;  // male, female
    private double retailPrice; //price


    private final MaterialList materials = new MaterialList();

    public JewelleryItem(String description, String type, String targetGender,
                         double retailPrice) {
        this.description = description;
        this.type = type;
        this.targetGender = targetGender;
        this.retailPrice = retailPrice;
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public MaterialList getMaterials() {
        return materials;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    // Getters/setters

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getTargetGender() {
        return targetGender;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
    }


    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
}

