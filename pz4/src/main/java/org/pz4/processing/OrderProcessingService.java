package org.pz4.processing;

import org.pz4.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class OrderProcessingService<T extends Product> {
    private final ExecutorService executorService;
    private final ConcurrentHashMap<String, OrderProcessor<T>> orders;
    private final List<T> products;

    public OrderProcessingService(int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.orders = new ConcurrentHashMap<>();
        this.products = new ArrayList<>();
    }

    public void addProduct(T product) {
        products.add(product);
    }

    public void submitOrder(String orderId, T product) {
        OrderProcessor<T> processor = new OrderProcessor<>(orderId, product);
        orders.put(orderId, processor);

        Runnable orderTask = () -> {
            processor.process();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("  ✓ Замовлення #" + orderId + " оброблено. Сума: " + processor.calculateTotal() + "₴\n");
        };

        executorService.submit(orderTask);
    }

    public void processOrdersWithLambda(Consumer<OrderProcessor<T>> action) {
        orders.values().forEach(action);
    }

    public void processOrdersWithMethodReference(java.util.function.Consumer<OrderProcessor<T>> action) {
        orders.values().forEach(action);
    }

    public List<T> filterByType(Class<? extends T> type) {
        return products.stream()
                .filter(type::isInstance)
                .collect(Collectors.toList());
    }

    public double getTotalAmount() {
        return orders.values().stream()
                .mapToDouble(OrderProcessor::calculateTotal)
                .sum();
    }

    public void shutdown() {
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

