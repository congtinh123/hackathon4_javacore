package HCM_JV240304_BS_HuynhCongTinh.ra.model;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String description;

    // Constructor không tham số
    public Catalog() {}

    // Constructor có tham số
    public Catalog(int catalogId, String catalogName, String description) {
        if (catalogName == null || catalogName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả không được để trống.");
        }
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
    }

    // Getter và Setter
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
        if (catalogName == null || catalogName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống.");
        }
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả không được để trống.");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return "Catalog ID: " + catalogId + ", Name: " + catalogName;
    }
}
