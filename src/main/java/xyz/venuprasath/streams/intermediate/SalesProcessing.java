package xyz.venuprasath.streams.intermediate;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

record Sale(LocalDate date, double amount) {}

public class SalesProcessing {
    List<Sale> sales;

    public SalesProcessing() {
        sales = Arrays.asList(
                new Sale(LocalDate.of(2023, 1, 15), 1000),
                new Sale(LocalDate.of(2023, 2, 10), 2000),
                new Sale(LocalDate.of(2023, 1, 20), 1500),
                new Sale(LocalDate.of(2023, 3, 5), 800),
                new Sale(LocalDate.of(2023, 2, 25), 2500)
        );
    }

    // Find the month with the highest sales
    public void findMonthWithHighestSales() {
        Map<Month, Double> map = sales.stream()
                .collect(Collectors.groupingBy(
                        s -> s.date().getMonth(),
                        Collectors.summingDouble(Sale::amount)
                ));

        Optional<Map.Entry<Month, Double>> entry = map.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue));

        entry.ifPresent(monthDoubleEntry ->
                System.out.println("\nMonth with highest sales: "
                        + monthDoubleEntry.getKey()));
    }
}
