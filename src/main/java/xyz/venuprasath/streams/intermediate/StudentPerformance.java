package xyz.venuprasath.streams.intermediate;

import java.util.*;
import java.util.stream.Collectors;

record Student(String name, List<Double> grades) {}

public class StudentPerformance {
    List<Student> students;

    public StudentPerformance() {
        students = Arrays.asList(
                new Student("John", Arrays.asList(90.0, 89.0, 79.0, 95.0)),
                new Student("Joe", Arrays.asList(92.0, 88.0, 84.0, 92.0)),
                new Student("Jack", Arrays.asList(80.0, 85.0, 75.0, 93.0)),
                new Student("Jim", Arrays.asList(92.0, 88.0, 78.0, 91.0)),
                new Student("Jane", Arrays.asList(91.0, 86.0, 89.0, 98.0))
        );
    }

    //Average grade per student
    public void averageGradePerStudent() {
        Map<String, Double> map = students.stream()
                .collect(Collectors.toMap(
                        Student::name,
                        student -> student.grades().stream()
                                .mapToDouble(Double::doubleValue)
                                .average().orElse(Double.NaN)
                        )
                );

        System.out.println("\nAverage grades: \n"+map);

        Optional<Map.Entry<String, Double>> answer = map.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue));

        if(answer.isPresent()) {
            Map.Entry<String, Double> entry = answer.get();
            System.out.println("\nThe student with the highest avg grade: " + entry.getKey());
        }

    }

}
