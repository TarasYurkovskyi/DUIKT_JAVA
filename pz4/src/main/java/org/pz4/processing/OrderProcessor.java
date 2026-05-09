package org.pz4.processing;

import lombok.Getter;
import org.pz4.domain.Product;

@Getter
public class OrderProcessor<T extends Product> {
    private final T product;
    private final String orderId;

    public OrderProcessor(String orderId, T product) {
        this.orderId = orderId;
        this.product = product;
    }

    public void process() {
        System.out.println("[" + Thread.currentThread().getName() + "] Обробка замовлення #" + orderId);
        System.out.println("  Тип товару: " + product.getProductType());
        System.out.println("  Назва: " + product.getName());
        System.out.println("  Ціна: " + product.getPrice() + "₴");
        System.out.println("  Опис: " + product.getDescription());
    }

    public double calculateTotal() {
        return product.getPrice() * 1.1;
    }
}

