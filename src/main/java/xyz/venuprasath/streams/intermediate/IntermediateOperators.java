package xyz.venuprasath.streams.intermediate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


record Employee(int id, int managerId, String name, double salary, String department) {}

public class IntermediateOperators {

    List<String> fruits;

    public IntermediateOperators() {
        // Source data
        fruits = Arrays.asList("Apple", "Banana", "Apple", "Orange", "Banana", "Grapes");
    }

    public void usingFilters() {
        //fruit names of length <= 5
        List<String> fruitSubList = fruits.stream()
                .filter((f) -> f.length() <= 5)
                .collect(Collectors.toUnmodifiableList());

        //fruitSubList.add("extraItem");  // throws UnsupportedOperationException

        System.out.println("\nFruit names of length <= 5\n" + fruitSubList);
    }

    public void usingMap() {
        List<String> fruitsMapped = fruits.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("\nFruits mapped to uppercase: " + fruitsMapped);
    }

    public void usingFlatMap() {
        System.out.println("\nFlatMap Operation");
        List<List<String>> nestedFruits = Arrays.asList(
                Arrays.asList("Apple", "Banana"),
                Arrays.asList("Orange", "Grapes")
        );

        var flatFruits = nestedFruits.stream().flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());

        System.out.println(flatFruits);
    }

    public void sorting() {
        System.out.println("\nSorted Operation");
        List<String> sortedFruits = fruits.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Fruits sorted alphabetically: " + sortedFruits);
    }

    public void findindDistinct() {
        System.out.println("\nDistinct Operation");
        List<String> uniqueFruits = fruits.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique fruits: " + uniqueFruits);
    }

    public void findingPeek() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    public void usingSkipAndLimit() {
        System.out.println("\nLimit & Skip Operations");
        List<String> limitedFruits = fruits.stream()
                .skip(2)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Fruits after skipping 2 and limiting to 3: " + limitedFruits);
    }

    public void usingGroupingBy() {
        List<Employee> employees = List.of(
                new Employee(1, 2, "John Doe", 50000, "Sales"),
                new Employee(2, 5, "Jane Doe", 60000, "Marketing"),
                new Employee(3, 5, "Bob Smith", 70000, "Sales"),
                new Employee(4, 2, "Alice Brown", 55000, "Marketing"),
                new Employee(5, 5, "Mike Davis", 65000, "Sales")
        );

        //Grouping By department
        Map<String, List<Employee>> groupedEmps = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        System.out.println("\nGrouped by dept: "+groupedEmps);

        //Grouping by Managers
        var response = employees.stream()
                .collect(Collectors.groupingBy(Employee::managerId));

        System.out.println(response);
    }
}
