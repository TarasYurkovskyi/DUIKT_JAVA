package org.pz1.service;

import org.pz1.domain.Product;
import org.pz1.domain.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchService {
    private List<Product> allProducts;

    public ProductSearchService(List<Product> allProducts) {
        this.allProducts = new ArrayList<>(allProducts);
    }

    public List<Product> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchTerm = name.toLowerCase();
        return allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    public List<Product> searchByCategory(Category category) {
        if (category == null) {
            return new ArrayList<>();
        }
        return allProducts.stream()
                .filter(p -> p.getCategory() != null && p.getCategory().getId() == category.getId())
                .collect(Collectors.toList());
    }

    public List<Product> searchByNameOrCategory(String name, Category category) {
        List<Product> result = new ArrayList<>(searchByName(name));
        List<Product> categoryResults = searchByCategory(category);

        for (Product p : categoryResults) {
            if (!result.contains(p)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        return allProducts.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Product searchById(int id) {
        return allProducts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        if (product != null && !allProducts.contains(product)) {
            allProducts.add(product);
        }
    }

    public void removeProduct(Product product) {
        allProducts.remove(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(allProducts);
    }
}
