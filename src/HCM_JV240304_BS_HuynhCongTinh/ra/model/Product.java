package HCM_JV240304_BS_HuynhCongTinh.ra.model;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;

    // Constructor không tham số
    public Product() {
        this.status = true; // mặc định là true
    }

    // Constructor có tham số
    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog) {
        if (productId == null || !productId.matches("P\\d{4}")) {
            throw new IllegalArgumentException("Mã sản phẩm không hợp lệ.");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống.");
        }
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0.");
        }
        if (stock < 10) {
            throw new IllegalArgumentException("Số lượng sản phẩm phải ít nhất là 10.");
        }
        if (catalog == null) {
            throw new IllegalArgumentException("Danh mục không được để null.");
        }

        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = true; // mặc định là true
    }

    // Getter và Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        if (productId == null || !productId.matches("P\\d{4}")) {
            throw new IllegalArgumentException("Mã sản phẩm không hợp lệ.");
        }
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống.");
        }
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0.");
        }
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 10) {
            throw new IllegalArgumentException("Số lượng sản phẩm phải ít nhất là 10.");
        }
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        if (catalog == null) {
            throw new IllegalArgumentException("Danh mục không được để null.");
        }
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                ", Name: " + productName +
                ", Price: " + productPrice +
                ", Description: " + description +
                ", Stock: " + stock +
                ", Catalog: " + catalog.getCatalogName() +
                ", Status: " + (status ? "Bán" : "Không bán");
    }
}
