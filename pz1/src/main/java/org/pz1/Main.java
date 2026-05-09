package org.pz1;

import org.pz1.domain.*;
import org.pz1.service.OrderHistoryService;
import org.pz1.service.ProductSearchService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Category electronics = new Category(1, "Електроніка");
        Category books = new Category(2, "Книги");
        Category clothing = new Category(3, "Одяг");

        System.out.println("✓ Категорії створено:");
        System.out.println("  - " + electronics.getName());
        System.out.println("  - " + books.getName());
        System.out.println("  - " + clothing.getName());

        // Создание товаров
        Product laptop = new Product(1, "Ноутбук Dell XPS 13", 1500.0, "Компактний та потужний ноутбук", electronics);
        Product mouse = new Product(2, "Бездротова мишка", 25.0, "Комфортна мишка для роботи", electronics);
        Product keyboard = new Product(3, "Механічна клавіатура", 120.0, "Якісна RGB клавіатура", electronics);
        Product javaBook = new Product(4, "Java для початківців", 35.0, "Вчимо Java з нуля", books);
        Product pythonBook = new Product(5, "Python Advanced", 45.0, "Продвинутий рівень Python", books);
        Product shirt = new Product(6, "Програміст T-shirt", 15.0, "T-shirt для розробників", clothing);

        List<Product> allProducts = List.of(laptop, mouse, keyboard, javaBook, pythonBook, shirt);

        System.out.println("\n✓ Товари створено: " + allProducts.size() + " шт.");

        System.out.println("\n" + "=".repeat(68));
        System.out.println("🔍 ЕТАП 2: Пошук товарів\n");

        ProductSearchService searchService = new ProductSearchService(allProducts);

        System.out.println("📌 Пошук за назвою 'Ноутбук':");
        List<Product> nameResults = searchService.searchByName("Ноутбук");
        nameResults.forEach(p -> System.out.println("  ✓ " + p.getName() + " - " + p.getPrice() + "₴"));

        // Пошук за категорією
        System.out.println("\n📌 Пошук за категорією 'Електроніка':");
        List<Product> categoryResults = searchService.searchByCategory(electronics);
        categoryResults.forEach(p -> System.out.println("  ✓ " + p.getName() + " - " + p.getPrice() + "₴"));

        // Пошук за ціною
        System.out.println("\n📌 Пошук товарів від 30до 150₴:");
        List<Product> priceResults = searchService.searchByPriceRange(30, 150);
        priceResults.forEach(p -> System.out.println("  ✓ " + p.getName() + " - " + p.getPrice() + "₴"));

        // Пошук за ID
        System.out.println("\n📌 Пошук товару з ID 4:");
        Product found = searchService.searchById(4);
        if (found != null) {
            System.out.println("  ✓ Знайдено: " + found.getName() + " (" + found.getCategory().getName() + ")");
        }

        // ============= КОШИК =============
        System.out.println("\n" + "=".repeat(68));
        System.out.println("🛒 ЕТАП 3: Робота з кошиком\n");

        Cart cart = new Cart();

        System.out.println("➕ Додавання товарів в кошик:");
        cart.addProduct(laptop);
        System.out.println("  ✓ " + laptop.getName() + " додано");

        cart.addProduct(mouse);
        System.out.println("  ✓ " + mouse.getName() + " додано");

        cart.addProduct(javaBook);
        System.out.println("  ✓ " + javaBook.getName() + " додано");

        System.out.println("\n📊 Стан кошика:");
        System.out.println("  Товарів в кошику: " + cart.getProductCount());
        System.out.println("  Загальна вартість: " + cart.getTotalPrice() + "₴");

        // Видалення товару
        System.out.println("\n➖ Видалення товару з ID 2 (Бездротова мишка):");
        boolean removed = cart.removeProductById(2);
        System.out.println("  ✓ Видалено: " + (removed ? "ДА" : "НІ"));

        System.out.println("\n📊 Оновлений стан кошика:");
        System.out.println("  Товарів в кошику: " + cart.getProductCount());
        cart.getProducts().forEach(p -> System.out.println("    - " + p.getName() + ": " + p.getPrice() + "₴"));
        System.out.println("  Загальна вартість: " + cart.getTotalPrice() + "₴");

        // ============= ЗАМОВЛЕННЯ =============
        System.out.println("\n" + "=".repeat(68));
        System.out.println("📋 ЕТАП 4: Робота з замовленнями і історією\n");

        OrderHistoryService orderService = new OrderHistoryService();

        // Реєстрація користувачів
        User user1 = new User(1, "Іван Петренко", "ivan@email.com");
        User user2 = new User(2, "Марія Коваленко", "maria@email.com");

        orderService.registerUser(user1);
        orderService.registerUser(user2);

        System.out.println("✓ Користувачі зареєстровані:");
        System.out.println("  - " + user1.getName() + " (" + user1.getEmail() + ")");
        System.out.println("  - " + user2.getName() + " (" + user2.getEmail() + ")");

        // Створення замовлення для користувача 1
        System.out.println("\n➕ Створення замовлень для користувача 1:");

        Cart order1Cart = new Cart();
        order1Cart.addProduct(laptop);
        order1Cart.addProduct(mouse);
        Order order1 = orderService.createOrder(1, order1Cart);
        System.out.println("  ✓ Замовлення #" + order1.getId() + " створено на суму " + order1.getTotalPrice() + "₴");

        Cart order2Cart = new Cart();
        order2Cart.addProduct(javaBook);
        order2Cart.addProduct(pythonBook);
        Order order2 = orderService.createOrder(1, order2Cart);
        System.out.println("  ✓ Замовлення #" + order2.getId() + " створено на суму " + order2.getTotalPrice() + "₴");

        // Створення замовлення для користувача 2
        System.out.println("\n➕ Створення замовлення для користувача 2:");
        Cart order3Cart = new Cart();
        order3Cart.addProduct(keyboard);
        order3Cart.addProduct(shirt);
        Order order3 = orderService.createOrder(2, order3Cart);
        System.out.println("  ✓ Замовлення #" + order3.getId() + " створено на суму " + order3.getTotalPrice() + "₴");

        // Оновлення статусів
        System.out.println("\n🔄 Оновлення статусів замовлень:");
        orderService.updateOrderStatus(1, order1.getId(), "Обробляється");
        System.out.println("  ✓ Замовлення #" + order1.getId() + " - статус: " + order1.getStatus());

        orderService.updateOrderStatus(1, order2.getId(), "Доставлено");
        System.out.println("  ✓ Замовлення #" + order2.getId() + " - статус: " + order2.getStatus());

        // ============= ІСТОРІЯ ЗАМОВЛЕНЬ =============
        System.out.println("\n" + "=".repeat(68));
        System.out.println("📚 ЕТАП 5: Історія замовлень користувачів\n");

        System.out.println("👤 Користувач: " + user1.getName());
        System.out.println("✉️  Email: " + user1.getEmail());
        System.out.println("📊 Статистика:");
        System.out.println("  - Всього замовлень: " + orderService.getUserTotalOrders(1));
        System.out.println("  - Всього витрачено: " + orderService.getUserTotalSpent(1) + "₴");

        System.out.println("\n📋 Замовлення користувача 1:");
        List<Order> userOrders = orderService.getUserOrderHistory(1);
        for (Order order : userOrders) {
            System.out.println("\n  Замовлення #" + order.getId() + ":");
            System.out.println("  Статус: " + order.getStatus());
            System.out.println("  Сума: " + order.getTotalPrice() + "₴");
            System.out.println("  Дата: " + order.getCreatedAt());
            System.out.println("  Товари:");
            order.getProducts().forEach(p -> System.out.println("    - " + p.getName() + " (" + p.getPrice() + "₴)"));
        }

        System.out.println("\n\n👤 Користувач: " + user2.getName());
        System.out.println("✉️  Email: " + user2.getEmail());
        System.out.println("📊 Статистика:");
        System.out.println("  - Всього замовлень: " + orderService.getUserTotalOrders(2));
        System.out.println("  - Всього витрачено: " + orderService.getUserTotalSpent(2) + "₴");

        // Замовлення за статусом
        System.out.println("\n" + "=".repeat(68));
        System.out.println("🔎 ЕТАП 6: Фільтрація замовлень за статусом\n");

        System.out.println("📌 Замовлення користувача 1 зі статусом 'Нове':");
        List<Order> newOrders = orderService.getOrdersByStatus(1, "Нове");
        if (newOrders.isEmpty()) {
            System.out.println("  ℹ️  Замовлень зі статусом 'Нове' не знайдено");
        } else {
            newOrders.forEach(o -> System.out.println("  ✓ Замовлення #" + o.getId() + " - " + o.getStatus()));
        }

        System.out.println("\n📌 Замовлення користувача 1 зі статусом 'Обробляється':");
        List<Order> processingOrders = orderService.getOrdersByStatus(1, "Обробляється");
        processingOrders.forEach(o -> System.out.println("  ✓ Замовлення #" + o.getId() + " - " + o.getStatus()));

        System.out.println("\n📌 Замовлення користувача 1 зі статусом 'Доставлено':");
        List<Order> deliveredOrders = orderService.getOrdersByStatus(1, "Доставлено");
        deliveredOrders.forEach(o -> System.out.println("  ✓ Замовлення #" + o.getId() + " - " + o.getStatus()));
    }
}

