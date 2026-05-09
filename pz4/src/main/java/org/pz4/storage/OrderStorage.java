package org.pz4.storage;

import org.pz4.domain.Product;
import org.pz4.processing.OrderProcessor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderStorage<T extends Product> {
    private CopyOnWriteArrayList<OrderProcessor<T>> processedOrders;

    public OrderStorage() {
        this.processedOrders = new CopyOnWriteArrayList<>();
    }

    public void save(OrderProcessor<T> orderProcessor) {
        processedOrders.add(orderProcessor);
    }

    public List<OrderProcessor<T>> getAll() {
        return Collections.unmodifiableList(processedOrders);
    }

    public OrderProcessor<T> getById(String orderId) {
        return processedOrders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public int getOrderCount() {
        return processedOrders.size();
    }

    public void clear() {
        processedOrders.clear();
    }
}

