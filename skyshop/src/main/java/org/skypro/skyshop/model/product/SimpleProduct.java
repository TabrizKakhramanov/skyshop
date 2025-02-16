package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product{
    private int productPrice;

    public SimpleProduct(UUID id,String productName, int productPrice) {
        super(id, productName);
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Цена должна быть больше 0!");
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getProductPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
