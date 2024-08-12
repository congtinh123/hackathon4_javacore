package HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    // Constructor
    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    // Getters and Setters
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Catalog ID: " + catalogId + ", Name: " + catalogName + ", Descriptions: " + descriptions;
    }

}
