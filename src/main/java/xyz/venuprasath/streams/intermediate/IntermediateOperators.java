package xyz.venuprasath.streams.intermediate;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


record Employee(int id, int managerId, String name, double salary, String department) {}

public class IntermediateOperators {

    List<String> fruits;
    List<Employee> employees;

    public IntermediateOperators() {
        // Source data
        fruits = Arrays.asList("Apple", "Banana", "Apple", "Orange", "Banana", "Grapes");
        employees = List.of(
                new Employee(1, 2, "John Doe", 50000, "Sales"),
                new Employee(2, 5, "Jane Doe", 60000, "Marketing"),
                new Employee(3, 5, "Bob Smith", 70000, "Sales"),
                new Employee(4, 2, "Alice Brown", 55000, "Marketing"),
                new Employee(5, 5, "Mike Davis", 65000, "Sales")
        );
    }

    public void usingFilters() {
        //fruit names of length <= 5
        List<String> fruitSubList = fruits.stream()
                .filter((f) -> f.length() <= 5)
                .toList();

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
                .toList();

        System.out.println(flatFruits);
    }

    public void sorting() {
        System.out.println("\nSorted Operation");
        List<String> sortedFruits = fruits.stream()
                .sorted()
                .toList();
        System.out.println("Fruits sorted alphabetically: " + sortedFruits);
    }

    public void findindDistinct() {
        System.out.println("\nDistinct Operation");
        List<String> uniqueFruits = fruits.stream()
                .distinct()
                .toList();
        System.out.println("Unique fruits: " + uniqueFruits);
    }

    public void findingPeek() {
        System.out.println("\nFinding Peak: ");
        List<String> result = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .toList();
    }

    public void usingSkipAndLimit() {
        System.out.println("\nLimit & Skip Operations");
        List<String> limitedFruits = fruits.stream()
                .skip(2)
                .limit(3)
                .toList();
        System.out.println("Fruits after skipping 2 and limiting to 3: " + limitedFruits);
    }

    public void usingGroupingBy() {
        //Grouping By department
        Map<String, List<Employee>> groupedEmps = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        System.out.println("\nGrouped by dept: "+groupedEmps);

        //Grouping by Managers
        var response = employees.stream()
                .collect(Collectors.groupingBy(Employee::managerId));

        System.out.println("\nGrouped By manager: " + response);
    }

    public void findTotalSalaryPerDept() {
        Map<String, Double> totalSalaryPerDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.summingDouble(Employee::salary)));

        System.out.println("\nTotal Salary Per department: " + totalSalaryPerDept);
    }

    public void findingAvgSalaryPerDept() {
        Map<String, Double> avgSalaryPerDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.averagingDouble(Employee::salary)));

        System.out.println("\nAverage Salary Per department: " + avgSalaryPerDept);
    }

    public void highestPaidEmployeesPerDept() {
        System.out.println("\nHighest paid employee per department:");
        Map<String, Optional<Employee>> highestPaidEmpPerDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));

        highestPaidEmpPerDept.forEach((dept, emp) -> emp.ifPresent(System.out::println));
    }
}
