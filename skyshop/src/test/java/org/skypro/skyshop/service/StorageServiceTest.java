package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    public void setUp() {
        storageService = new StorageService();
    }

    // 1. Проверка, что все продукты загружаются
    @Test
    public void testGetAllProducts_NotEmpty() {
        Map<UUID, Product> products = storageService.getAllProducts();

        assertFalse(products.isEmpty(), "Продукты должны быть загружены в конструкторе");
        assertEquals(5, products.size(), "Ожидается 5 продуктов (3 simple, 1 fix, 1 discounted)");
    }

    // 2. Проверка, что все статьи загружаются
    @Test
    public void testGetAllArticles_NotEmpty() {
        Map<UUID, Article> articles = storageService.getAllArticles();

        assertFalse(articles.isEmpty(), "Статьи должны быть загружены в конструкторе");
        assertEquals(2, articles.size(), "Ожидается 2 статьи");
    }

    // 3. Получение продукта по корректному ID
    @Test
    public void testGetProductById_Success() {
        UUID existingId = storageService.getAllProducts().keySet().iterator().next();

        Optional<Product> product = storageService.getProductById(existingId);

        assertTrue(product.isPresent(), "Продукт должен быть найден");
        assertEquals(existingId, product.get().getId(), "ID продукта должен совпадать");
    }

    // 4. Получение продукта по несуществующему ID — должно выбросить исключение
    @Test
    public void testGetProductById_NotFound_ThrowsException() {
        UUID fakeId = UUID.randomUUID();

        NoSuchProductException thrown = assertThrows(
                NoSuchProductException.class,
                () -> storageService.getProductById(fakeId),
                "Ожидается NoSuchProductException при отсутствии продукта"
        );

        assertTrue(thrown.getMessage().contains(fakeId.toString()));
    }

    // 5. Проверка комбинированного списка getAllItems
    @Test
    public void testGetAllItems_ReturnsProductsAndArticles() {
        List<Searchable> items = storageService.getAllItems();

        assertEquals(7, items.size(), "Ожидается 5 продуктов + 2 статьи = 7 элементов");
    }
}

