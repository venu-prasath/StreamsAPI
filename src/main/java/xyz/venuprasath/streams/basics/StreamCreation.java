package xyz.venuprasath.streams.basics;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamCreation {

    public StreamCreation() {

    }

    public void fromCollection() {
        List<String> namesList = Arrays.asList("Alice", "Bob", "Charlie", "Darbie", "Eric");
        Stream<String> collectionStream = namesList.stream();
        System.out.println("Stream from Collection:");
        collectionStream.forEach(System.out::println);
    }

    public void usingDotOf() {
        Stream<String> streamOf = Stream.of("AAA", "BBB", "CCC", "DDD");
        System.out.println("\nStream using Stream.of():");
        streamOf.forEach(System.out::println);
    }

    public void usingGenerate() {
        System.out.println("\nInfinite Stream using Stream.generate():");
        Stream<Integer> generatedStream = Stream.generate(() ->
                        new Random().nextInt(100))
                .limit(5);
        generatedStream.forEach(System.out::println);
    }

    public void fromArray() {
        System.out.println("\nStream from Arrays.stream()");
        String[] array = {"AA", "BB", "CC", "DD", "EE"};
        Stream<String> stream = Arrays.stream(array);
        stream.forEach(System.out::println);
    }

    public void usingBuilder() {
        System.out.println("\nStream using builder");
        Stream<String> stream = Stream.<String>builder()
                .add("Item 1")
                .add("Item 2")
                .add("Item 3")
                .build();
        stream.forEach(System.out::println);
    }
}
