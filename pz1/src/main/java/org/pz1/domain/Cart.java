package org.pz1.domain;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {
    private final List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public boolean removeProductById(int productId) {
        return products.removeIf(p -> p.getId() == productId);
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public int getProductCount() {
        return products.size();
    }
}
