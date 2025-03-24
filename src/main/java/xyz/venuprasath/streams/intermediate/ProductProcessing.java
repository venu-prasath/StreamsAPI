package xyz.venuprasath.streams.intermediate;

import java.util.Arrays;
import java.util.List;

record Product(int id, double price) {}

public class ProductProcessing {

    List<Product> products;

    public ProductProcessing() {
        products = Arrays.asList(
                new Product(101 ,5999),
                new Product(102 ,899),
                new Product(103 ,1099),
                new Product(104 ,4500),
                new Product(105 ,1299)
        );
    }

    // Extract product ids whose price is greater that 4000
    public void productsWithPriceAbove4000() {
        List<Integer> filteredProducts = products.stream()
                .filter(prod -> prod.price() > 4000)
                .map(Product::id)
                .toList();

        System.out.println("\nProducts above $4000\n"+filteredProducts);
    }

    // Ids of budget friendly products
    public void budgetFriendlyProducts() {
        boolean hasBudgetFriendlyProducts = products.stream()
                .anyMatch(prod -> prod.price() < 1000);

        System.out.println("\nAre there any budget friendly products: "+hasBudgetFriendlyProducts);
    }
}
