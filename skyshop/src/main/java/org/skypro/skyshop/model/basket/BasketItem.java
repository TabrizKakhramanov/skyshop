package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private Product product;
    private int productQuantity;

    public BasketItem(Product product, int quantity){
        this.product = product;
        this.productQuantity=quantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public Product getProduct() {
        return product;
    }
}
