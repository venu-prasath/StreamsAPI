package xyz.venuprasath.streams.advanced;

import java.util.*;
import java.util.stream.Collectors;

record Employee(String name, String dept, double salary, String role) {}

record Invoice(String customer, double amount) {}

record LogEntry(String severity, String errorCode, String msg) {}

record Department(String name, List<Employee> employees) {}

public class MultiStage {
    List<Employee> employees;
    List<Invoice> invoices;
    List<LogEntry> logs;
    List<Department> departments;

    public MultiStage() {
        employees = Arrays.asList(
                new Employee("John", "Engineering", 80000, "Manager"),
                new Employee("Alice", "Engineering", 85000, "Dev"),
                new Employee("Bob", "Marketing", 60000, "Manager"),
                new Employee("Charlie", "Marketing", 65000, "Manager"),
                new Employee("Diana", "Sales", 55000, "Dev")
        );

        invoices = Arrays.asList(
                new Invoice("A", 1000),
                new Invoice("B", 2000),
                new Invoice("A", 1500),
                new Invoice("C", 800),
                new Invoice("B", 2500)
        );

        logs = Arrays.asList(
                new LogEntry("ERROR", "E001", "System crash"),
                new LogEntry("WARNING", "W001", "Low memory"),
                new LogEntry("ERROR", "E002", "Network issue"),
                new LogEntry("WARNING", "W001", "Disk space low"),
                new LogEntry("ERROR", "E001", "Service failed")
        );

        departments = Arrays.asList(
                new Department("Engineering",
                        Arrays.asList(
                                new Employee("John", "IT", 10000, "Manager"),
                                new Employee("Alice", "HR", 20000, "Developer")
                        )
                ),
                new Department("Marketing",
                        Arrays.asList(
                                new Employee("Bob", "Marketing", 20000, "Manager"),
                                new Employee("Charlie", "Marketing", 20000, "Developer")
                        )
                )
        );
    }

    //Find dept with Highest avg salary and find the top earner
    public void findDeptWithHighestAvgSalaryAndTopEarner() {
        System.out.println("\nTop Earner from Dept with highest average");
        Map<String, Double> map = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingDouble(Employee::salary)));

        Optional<Map.Entry<String, Double>> entry = map.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue));

        entry.ifPresent(e -> {
            Optional<Employee> result = employees.stream()
                    .filter(emp -> Objects.equals(emp.dept(), e.getKey()))
                    .max(Comparator.comparingDouble(Employee::salary));

            result.ifPresent(System.out::println);
        });
    }

    // Calculate total, apply discounts, summarize results
    public void invoiceCalculation() {
        double total = invoices.stream()
                .collect(Collectors.summingDouble(Invoice::amount));

        System.out.println("\nInvoice total: $"+total);

        //apply 10% discounts to invoices over $1000
        double afterDiscount = invoices.stream()
                .mapToDouble(inv -> inv.amount() > 1000 ?
                        inv.amount() * 0.9 : inv.amount())
                .sum();

        System.out.println("\nTotal after discount: $"+afterDiscount);

        //group by customers
        Map<String, Double> groupedResult = invoices.stream()
                .collect(Collectors.groupingBy(
                        Invoice::customer,
                        Collectors.summingDouble(Invoice::amount)
                ));

        System.out.println("\nGrouped by customers: "+groupedResult);

    }

    // filter by severity, group by ec, count occurences
    public void processLogs() {
        Map<String, Long> processedLogs = logs.stream()
                .filter(f -> f.severity().equals("ERROR"))
                .collect(Collectors.groupingBy(
                        LogEntry::errorCode,
                        Collectors.counting()
                ));

        System.out.println("\nProcessed Logs: "+processedLogs);
    }

    // get all managers from all departments
    public void getAllManagers() {
        List<Employee> managers = departments.stream()
                .flatMap(dept -> dept.employees().stream())
                .filter(e -> e.role().equals("Manager"))
                .toList();

        System.out.println("\nManagers: "+managers);
    }
}
