package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    // 1. Добавление несуществующего товара — выбрасывается IllegalArgumentException
    @Test
    public void testAddNonExistentProduct_ThrowsException() {
        UUID fakeId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(fakeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> basketService.addProductById(fakeId));
        Mockito.verify(productBasket, Mockito.never()).addProduct(Mockito.any());
    }

    // 2. Добавление существующего товара — вызывается addProduct
    @Test
    public void testAddExistingProduct_CallsAddProduct() {
        UUID existingId = UUID.randomUUID();
        Product mockProduct = new SimpleProduct(existingId, "milk", 90);

        Mockito.when(storageService.getProductById(existingId)).thenReturn(Optional.of(mockProduct));

        basketService.addProductById(existingId);

        Mockito.verify(productBasket).addProduct(existingId);
    }

    // 3. Получение пустой корзины — выбрасывается NoSuchElementException
    @Test
    public void testGetUserBasket_EmptyBasket_ThrowsException() {
        Mockito.when(productBasket.getAllProducts()).thenReturn(Collections.emptyMap());

        assertThrows(NoSuchElementException.class, () -> basketService.getUserBasket());
    }

    // 4. Получение полной корзины с одним товаром
    @Test
    public void testGetUserBasket_WithProducts_ReturnsUserBasket() {
        UUID productId = UUID.randomUUID();
        Product mockProduct = new SimpleProduct(productId, "bread", 50);
        Map<UUID, Integer> basketMap = Map.of(productId, 2);

        Mockito.when(productBasket.getAllProducts()).thenReturn(basketMap);
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        UserBasket basket = basketService.getUserBasket();

        assertNotNull(basket);
        assertEquals(1, basket.getBasketItems().size());
    }
}
