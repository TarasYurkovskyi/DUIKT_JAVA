package org.pz4.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductFactory {
    private static final Random random = new Random();
    private static int id = 1;

    private static final String[] brands = {"Samsung", "LG", "Sony", "Apple", "Dell", "ASUS", "HP", "Lenovo"};
    private static final String[] electronicsNames = {"Смартфон", "Ноутбук", "Планшет", "Монітор", "Навушники", "Клавіатура"};
    private static final String[] warranties = {"1 рік", "2 роки", "3 роки", "5 років"};

    private static final String[] clothingTypes = {"Футболка", "Штани", "Сукня", "Рубашка", "Кофта", "Шорти"};
    private static final String[] sizes = {"XS", "S", "M", "L", "XL", "XXL"};
    private static final String[] colors = {"Чорний", "Білий", "Червоний", "Синій", "Зелений", "Жовтий", "Сірий"};
    private static final String[] materials = {"Бавовна", "Поліестер", "Шовк", "Лляне полотно", "Вовна"};

    public static Electronics createRandomElectronics() {
        String brand = brands[random.nextInt(brands.length)];
        String productType = electronicsNames[random.nextInt(electronicsNames.length)];
        double price = 100 + random.nextDouble() * 1900;
        String warranty = warranties[random.nextInt(warranties.length)];

        return Electronics.builder()
                .id(id++)
                .name(brand + " " + productType)
                .price(price)
                .description("Якісний товар від " + brand)
                .brand(brand)
                .warranty(warranty)
                .build();
    }

    public static Clothing createRandomClothing() {
        String clothingType = clothingTypes[random.nextInt(clothingTypes.length)];
        double price = 10 + random.nextDouble() * 490;
        String size = sizes[random.nextInt(sizes.length)];
        String color = colors[random.nextInt(colors.length)];
        String material = materials[random.nextInt(materials.length)];

        return Clothing.builder()
                .id(id++)
                .name(clothingType + " " + color)
                .price(price)
                .description("Комбортний одяг з " + material)
                .size(size)
                .color(color)
                .material(material)
                .build();
    }

    public static List<Product> createMixedProducts(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                products.add(createRandomElectronics());
            } else {
                products.add(createRandomClothing());
            }
        }
        return products;
    }
}







