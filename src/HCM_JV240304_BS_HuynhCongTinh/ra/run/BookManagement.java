package HCM_JV240304_BS_HuynhCongTinh.ra.run;

import HCM_JV240304_BS_HuynhCongTinh.ra.model.Catalog;
import HCM_JV240304_BS_HuynhCongTinh.ra.model.Product;
import HCM_JV240304_BS_HuynhCongTinh.ra.service.CatalogService;
import HCM_JV240304_BS_HuynhCongTinh.ra.service.ProductService;
import util.InputMethods;

public class BookManagement {
    private static final CatalogService catalogService = new CatalogService();
    private static final ProductService productService = new ProductService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");

            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    manageCatalog();
                    break;
                case 2:
                    manageProduct();
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void manageCatalog() {
        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý không xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");

            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    addCatalog();
                    break;
                case 2:
                    listCatalogs();
                    break;
                case 3:
                    updateCatalogName();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addCatalog() {
        System.out.println("Nhập số lượng danh mục:");
        int count = InputMethods.getInteger();
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập tên danh mục:");
            String name = InputMethods.getString();
            System.out.println("Nhập mô tả danh mục:");
            String description = InputMethods.getString();
            try {
                catalogService.save(new Catalog(0, name, description)); // 0 sẽ được gán ID tự động
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private static void listCatalogs() {
        if (catalogService.getAll().isEmpty()) {
            System.out.println("Không có danh mục nào.");
        } else {
            for (Catalog catalog : catalogService.getAll()) {
                System.out.println(catalog);
            }
        }
    }

    private static void updateCatalogName() {
        System.out.println("Nhập mã danh mục cần sửa:");
        int id = InputMethods.getInteger();
        Catalog catalog = catalogService.findById(id);
        if (catalog != null) {
            System.out.println("Nhập tên mới cho danh mục:");
            String newName = InputMethods.getString();
            try {
                catalog.setCatalogName(newName);
                System.out.println("Tên danh mục đã được cập nhật.");
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        } else {
            System.out.println("Danh mục không tồn tại.");
        }
    }

    private static void deleteCatalog() {
        System.out.println("Nhập mã danh mục cần xóa:");
        int id = InputMethods.getInteger();
        Catalog catalog = catalogService.findById(id);
        if (catalog != null) {
            boolean hasProduct = productService.getAll().stream().anyMatch(p -> p.getCatalog().getCatalogId() == id);
            if (hasProduct) {
                System.out.println("Danh mục có sản phẩm, không thể xóa.");
            } else {
                catalogService.delete(id);
                System.out.println("Danh mục đã được xóa.");
            }
        } else {
            System.out.println("Danh mục không tồn tại.");
        }
    }

    private static void manageProduct() {
        while (true) {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Thay đổi thông tin của sản phẩm theo mã");
            System.out.println("7. Quay lại");

            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    sortProductsByPrice();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    updateProductInfo();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Nhập số lượng sản phẩm:");
        int count = InputMethods.getInteger();
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập mã sản phẩm (PXXXX):");
            String id = InputMethods.getString();
            System.out.println("Nhập tên sản phẩm:");
            String name = InputMethods.getString();
            System.out.println("Nhập giá sản phẩm:");
            double price = InputMethods.getDouble();
            System.out.println("Nhập mô tả sản phẩm:");
            String description = InputMethods.getString();
            System.out.println("Nhập số lượng sản phẩm:");
            int stock = InputMethods.getInteger();

            // Hiển thị danh sách danh mục cho người dùng chọn
            System.out.println("Danh sách danh mục:");
            listCatalogs(); // Gọi phương thức để hiển thị danh sách danh mục
            System.out.println("Nhập mã danh mục:");
            int catalogId = InputMethods.getInteger();
            Catalog catalog = catalogService.findById(catalogId);
            if (catalog != null) {
                try {
                    productService.save(new Product(id, name, price, description, stock, catalog));
                    System.out.println("Sản phẩm đã được thêm.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Lỗi: " + e.getMessage());
                }
            } else {
                System.out.println("Danh mục không tồn tại.");
            }
        }
    }

    private static void listProducts() {
        if (productService.getAll().isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            for (Product product : productService.getAll()) {
                System.out.println(product);
            }
        }
    }

    private static void sortProductsByPrice() {
        productService.getAll().sort((p1, p2) -> Double.compare(p2.getProductPrice(), p1.getProductPrice()));
        listProducts();
    }

    private static void deleteProduct() {
        System.out.println("Nhập mã sản phẩm cần xóa:");
        String id = InputMethods.getString();
        Product product = productService.findById(id);
        if (product != null) {
            productService.delete(id);
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }

    private static void searchProductByName() {
        System.out.println("Nhập tên sản phẩm để tìm kiếm:");
        String name = InputMethods.getString();
        boolean found = false;
        for (Product product : productService.getAll()) {
            if (product.getProductName().contains(name)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    private static void updateProductInfo() {
        System.out.println("Nhập mã sản phẩm cần sửa:");
        String id = InputMethods.getString();
        Product product = productService.findById(id);
        if (product != null) {
            System.out.println("Nhập tên mới cho sản phẩm:");
            String newName = InputMethods.getString();
            System.out.println("Nhập giá mới cho sản phẩm:");
            double newPrice = InputMethods.getDouble();
            System.out.println("Nhập mô tả mới cho sản phẩm:");
            String newDescription = InputMethods.getString();
            System.out.println("Nhập số lượng mới cho sản phẩm:");
            int newStock = InputMethods.getInteger();

            try {
                product.setProductName(newName);
                product.setProductPrice(newPrice);
                product.setDescription(newDescription);
                product.setStock(newStock);
                System.out.println("Thông tin sản phẩm đã được cập nhật.");
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }
}
