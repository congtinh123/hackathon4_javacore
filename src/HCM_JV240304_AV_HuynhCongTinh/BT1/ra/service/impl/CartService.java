package HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.impl;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model.CartItem;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void displayAllProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }

        System.out.println("Danh sách sản phẩm:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addToCart(String productId, List<Product> products) {
        Product product = products.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
        if (product == null) {
            System.out.println("Sản phẩm không tồn tại.");
            return;
        }

        CartItem cartItem = cartItems.stream().filter(item -> item.getProduct().equals(product)).findFirst().orElse(null);
        if (cartItem == null) {
            cartItems.add(new CartItem(productId, product, product.getProductPrice(), 1));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        product.setStock(product.getStock() - 1);
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
            return;
        }

        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        for (CartItem item : cartItems) {
            System.out.println(item);
        }
    }

    public void updateCartItemQuantity(String cartItemId, int newQuantity) {
        CartItem cartItem = cartItems.stream().filter(item -> item.getCartItemId().equals(cartItemId)).findFirst().orElse(null);
        if (cartItem == null) {
            System.out.println("Sản phẩm không có trong giỏ hàng.");
            return;
        }

        Product product = cartItem.getProduct();
        int oldQuantity = cartItem.getQuantity();
        cartItem.setQuantity(newQuantity);
        product.setStock(product.getStock() + (oldQuantity - newQuantity));
    }

    public void removeCartItem(String cartItemId) {
        CartItem cartItem = cartItems.stream().filter(item -> item.getCartItemId().equals(cartItemId)).findFirst().orElse(null);
        if (cartItem == null) {
            System.out.println("Sản phẩm không có trong giỏ hàng.");
            return;
        }
        Product product = cartItem.getProduct();
        product.setStock(product.getStock() + cartItem.getQuantity());

        cartItems.remove(cartItem);
    }

    public void clearCart() {
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() + cartItem.getQuantity());
        }
        cartItems.clear();
    }
}

