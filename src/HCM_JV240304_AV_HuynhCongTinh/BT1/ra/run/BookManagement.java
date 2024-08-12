package HCM_JV240304_AV_HuynhCongTinh.BT1.ra.run;

import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model.Catalog;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model.Product;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.impl.CartService;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.impl.CatalogServiceImpl;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.impl.ProductServiceImpl;
import java.util.Scanner;

public class BookManagement {
    private static CatalogServiceImpl catalogService = new CatalogServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();
    private static CartService cartService = new CartService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Dành cho người dùng");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = -1;
            while (choice < 1 || choice > 3) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 3) {
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập một số nguyên!");
                }
            }

            switch (choice) {
                case 1:
                    manageCatalog();
                    break;
                case 2:
                    manageProduct();
                    break;
                case 3:
                    userMenu();
                case 4:
                    System.exit(0);
                    break;
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
            System.out.print("Chọn chức năng: ");

            int choice = -1;
            while (choice < 1 || choice > 5) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 5) {
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập một số nguyên!");
                }
            }

            switch (choice) {
                case 1:
                    addCatalogs();
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    updateCatalogName();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void addCatalogs() {
        System.out.print("Nhập số danh mục muốn thêm: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            int id = -1;
            while (id < 0) {
                try {
                    System.out.print("Nhập mã danh mục: ");
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Mã danh mục phải là số nguyên dương.");
                }
            }

            String name = "";
            while (name.trim().isEmpty()) {
                System.out.print("Nhập tên danh mục: ");
                name = scanner.nextLine();
                if (name.trim().isEmpty()) {
                    System.out.println("Tên danh mục không được để trống.");
                }
            }

            String description = "";
            while (description.trim().isEmpty()) {
                System.out.print("Nhập mô tả danh mục: ");
                description = scanner.nextLine();
                if (description.trim().isEmpty()) {
                    System.out.println("Mô tả danh mục không được để trống.");
                }
            }

            Catalog catalog = new Catalog(id, name, description);
            catalogService.save(catalog);
            System.out.println("Thêm mới thành công");
        }
    }


    private static void displayAllCatalogs() {
        System.out.println("Danh sách tất cả danh mục:");
        for (Catalog catalog : catalogService.getAll()) {
            System.out.println(catalog);
        }
    }

    private static void updateCatalogName() {
        System.out.print("Nhập mã danh mục cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findById(id);
        if (catalog != null) {
            System.out.print("Nhập tên mới: ");
            catalog.setCatalogName(scanner.nextLine());
        } else {
            System.out.println("Không tìm thấy danh mục với mã đã nhập.");
        }
    }

    private static void deleteCatalog() {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findById(id);
        if (catalog != null && !hasProductInCatalog(catalog)) {
            catalogService.delete(id);
            System.out.println("Xóa danh mục thành công.");
        } else {
            System.out.println("Không thể xóa danh mục vì có sản phẩm trong danh mục.");
        }
    }

    private static boolean hasProductInCatalog(Catalog catalog) {
        for (Product product : productService.getAll()) {
            if (product.getCatalog().equals(catalog)) {
                return true;
            }
        }
        return false;
    }

    private static void manageProduct() {
        while (true) {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = -1;
            while (choice < 1 || choice > 7) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 7) {
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập một số nguyên!");
                }
            }

            switch (choice) {
                case 1:
                    addProducts();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    sortProductsByPriceDescending();
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
            }
        }
    }

    private static void addProducts() {
        System.out.print("Nhập số sản phẩm muốn thêm: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String productId;
            do {
                System.out.print("Nhập mã sản phẩm (bắt đầu bằng chữ P và thêm 4 ký tự số): ");
                productId = scanner.nextLine();
                if (!productId.matches("P\\d{4}")) {
                    System.out.println("Mã sản phẩm phải bắt đầu bằng chữ P và có thêm 4 ký tự số.");
                    productId = null;
                } else if (productService.findById(productId) != null) {
                    System.out.println("Mã sản phẩm này đã tồn tại. Vui lòng nhập mã khác.");
                    productId = null;
                }
            } while (productId == null);

            String productName = "";
            while (productName.trim().isEmpty()) {
                System.out.print("Nhập tên sản phẩm: ");
                productName = scanner.nextLine();
                if (productName.trim().isEmpty()) {
                    System.out.println("Tên sản phẩm không được để trống.");
                }
            }

            double productPrice = -1;
            while (productPrice <= 0) {
                try {
                    System.out.print("Nhập giá sản phẩm: ");
                    productPrice = Double.parseDouble(scanner.nextLine());
                    if (productPrice <= 0) {
                        System.out.println("Giá sản phẩm phải lớn hơn 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Giá sản phẩm phải là một số hợp lệ.");
                }
            }

            System.out.print("Nhập mô tả sản phẩm: ");
            String description = scanner.nextLine();

            int stock = -1;
            while (stock < 10) {
                try {
                    System.out.print("Nhập số lượng tồn kho: ");
                    stock = Integer.parseInt(scanner.nextLine());
                    if (stock < 10) {
                        System.out.println("Số lượng tồn kho phải ít nhất là 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng tồn kho phải là một số nguyên.");
                }
            }

            Catalog catalog = null;
            while (catalog == null) {
                System.out.print("Chọn mã danh mục: ");
                int catalogId = Integer.parseInt(scanner.nextLine());
                catalog = catalogService.findById(catalogId);
                if (catalog == null) {
                    System.out.println("Mã danh mục không tồn tại. Vui lòng nhập mã khác.");
                }
            }

            Product product = new Product(productId, productName, productPrice, description, stock, catalog);
            productService.save(product);
            System.out.println("Thêm mới sản phẩm thành công.");
        }
    }


    private static void displayAllProducts() {
        System.out.println("Danh sách tất cả sản phẩm:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }

    private static void sortProductsByPriceDescending() {
        productService.getAll().sort((p1, p2) -> Double.compare(p2.getProductPrice(), p1.getProductPrice()));
        displayAllProducts();
    }

    private static void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.nextLine();
        productService.delete(productId);
    }

    private static void searchProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();
        for (Product product : productService.getAll()) {
            if (product.getProductName().contains(name)) {
                System.out.println(product);
            }
        }
    }

    private static void updateProductInfo() {
        System.out.print("Nhập mã sản phẩm cần thay đổi thông tin: ");
        String productId = scanner.nextLine();
        Product product = productService.findById(productId);
        if (product != null) {
            System.out.print("Nhập tên mới: ");
            product.setProductName(scanner.nextLine());
            System.out.print("Nhập giá mới: ");
            product.setProductPrice(Double.parseDouble(scanner.nextLine()));
            System.out.print("Nhập mô tả mới: ");
            product.setDescription(scanner.nextLine());
            System.out.print("Nhập số lượng tồn kho mới: ");
            product.setStock(Integer.parseInt(scanner.nextLine()));
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã đã nhập.");
        }
    }

    public static void userMenu() {
        while (true) {
            System.out.println("**************************MENU-USER**************************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. Quay lại");
            System.out.print("Chọn lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cartService.displayAllProducts(productService.getAll());
                    break;
                case 2:
                    System.out.print("Nhập mã sản phẩm để thêm vào giỏ hàng: ");
                    String productId = scanner.next();
                    cartService.addToCart(productId, productService.getAll());
                    break;
                case 3:
                    cartService.viewCart();
                    break;
                case 4:
                    System.out.print("Nhập mã sản phẩm trong giỏ hàng để thay đổi số lượng: ");
                    String cartItemId = scanner.next();
                    System.out.print("Nhập số lượng mới: ");
                    int newQuantity = scanner.nextInt();
                    cartService.updateCartItemQuantity(cartItemId, newQuantity);
                    break;
                case 5:
                    System.out.print("Nhập mã sản phẩm trong giỏ hàng để xóa: ");
                    cartItemId = scanner.next();
                    cartService.removeCartItem(cartItemId);
                    break;
                case 6:
                    cartService.clearCart();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
