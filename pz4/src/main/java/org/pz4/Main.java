package org.pz4;

import org.pz4.domain.*;
import org.pz4.processing.OrderProcessingService;
import org.pz4.storage.OrderStorage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderProcessingService<Product> processingService = new OrderProcessingService<>(3);
        OrderStorage<Product> storage = new OrderStorage<>();

        System.out.println("📦 ЕТАП 1: Генерування тестових товарів\n");
        List<Product> products = ProductFactory.createMixedProducts(10);
        products.forEach(p -> {
            processingService.addProduct(p);
            System.out.println("✓ [" + p.getProductType() + "] " + p.getName() + " - " + p.getPrice() + "₴");
        });

        System.out.println("\n" + "=".repeat(70));
        System.out.println("🔄 ЕТАП 2: Обробка замовлень багатопотоково\n");

        for (int i = 0; i < products.size(); i++) {
            processingService.submitOrder("ORD-" + (i + 1), products.get(i));
        }

        processingService.shutdown();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("🔎 ЕТАП 3: Фільтрація замовлень за типом\n");

        System.out.println("Електроніка:");
        List<Product> electronics = processingService.filterByType(Electronics.class);
        electronics.forEach(e -> System.out.println("  ✓ " + e.getName() + " - " + e.getPrice() + "₴"));

        System.out.println("\nОдяг:");
        List<Product> clothing = processingService.filterByType(Clothing.class);
        clothing.forEach(c -> System.out.println("  ✓ " + c.getName() + " - " + c.getPrice() + "₴"));

        System.out.println("\n" + "=".repeat(70));
        System.out.println("💰 ЕТАП 4: Розрахунки\n");

        double totalAmount = processingService.getTotalAmount();
        System.out.println("Загальна сума всіх замовлень: " + String.format("%.2f", totalAmount) + "₴");
        System.out.println("Збережено замовлень в сховищі: " + storage.getOrderCount());

        System.out.println("\n" + "=".repeat(70));
    }
}

