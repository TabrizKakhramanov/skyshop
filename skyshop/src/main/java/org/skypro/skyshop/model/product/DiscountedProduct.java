package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product{
    private int productPrice;
    private int discount;

    public DiscountedProduct(UUID id, String productName, int productPrice, int discount) {
        super(id, productName);
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Цена должна быть больше 0!");
        }
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        } else {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0% до 100%!");
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice * (100 - discount) / 100;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getProductPrice() + " скидка: " + getDiscount() + "%";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
