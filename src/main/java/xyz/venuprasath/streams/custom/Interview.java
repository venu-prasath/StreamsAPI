package xyz.venuprasath.streams.custom;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Interview {
    List<Integer> listOfIntegers;

    public Interview() {
        listOfIntegers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
        );
    }

    public void seperateOddAndEvenNumbers() {
        Map<Boolean, List<Integer>> map = listOfIntegers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));

        System.out.println("\nSeparating Odd and Even numbers: " + map);
    }

    public void removeDuplicateElementsFromList() {
        List<Integer> dupliatedList = new ArrayList(listOfIntegers);
        dupliatedList.add(3);
        dupliatedList.add(7);
        dupliatedList.add(4);

        System.out.println("\nFinal List before de-duplication: "+dupliatedList);
        var finalList = dupliatedList.stream().distinct().toList();
        System.out.println("Final List after de-duplication: "+finalList);
    }

    public void findFrequencyOfEachCharInString() {
        String str = "This is a sample string";
        var freqMap = str.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        System.out.println("\nFrequency map of string: " + freqMap);
    }

    public void findFrequencyOfEachElementInArray() {
        List<Integer> dupliatedList = new ArrayList(listOfIntegers);
        dupliatedList.add(3);
        dupliatedList.add(7);
        dupliatedList.add(4);

        var freqMap = dupliatedList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("\nFreq Map for list of integers: "+freqMap);
    }

    public void sortListInReverseOrder() {
        List<Integer> dupliatedList = new ArrayList(listOfIntegers);
        dupliatedList.add(3);
        dupliatedList.add(7);
        dupliatedList.add(4);

        var reversedList = dupliatedList.stream()
                .sorted(Comparator.reverseOrder()).toList();
        System.out.println("\nReversed Liset: "+reversedList);
    }

    public void joinStringsWithPrefixSuffixDelim() {
        List<String> listOfStrings = Arrays.asList(
                "AAA", "BBB", "CCC"
        );
        var joinedString = listOfStrings.stream().collect(
                Collectors.joining(":", "pre", "suf")
        );
        System.out.println("\nFinal String: "+joinedString);
    }

    public void findMaxAndMinFromList() {
        var max = listOfIntegers.stream()
                .max(Comparator.naturalOrder()).get();
        var min = listOfIntegers.stream()
                .min(Comparator.naturalOrder()).get();

        System.out.println("\nMax and Min: "+max +", "+min);
    }

    public void checkIfAnagrams() {
        String s1 = "raceCar";
        String s2 = "RacEcar";

        String s1New = Stream.of(s1.split(""))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining());

        String s2New = Stream.of(s2.split(""))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining());

        System.out.println("\nTwo srings are anagrams: "+s1New.equals(s2New));
    }

    public void sortListInIncreasingOrder() {
        System.out.println("\nSort list of strings in increasing order: ");
        List<String> listOfStrings = Arrays.asList(
                "XYYZ", "XZXZ", "AAA", "BBB", "CCC"
        );

        listOfStrings.stream()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);
    }

    public void reverseEachWordOfAString() {
        String str = "This is a sample string";

        var result = Stream.of(str.split(" "))
                .map(word -> new StringBuffer(word).reverse())
                .collect(Collectors.joining(" "));

        System.out.println("\nReversed: "+result);
    }

    public void sumOfFirst10NaturalNumbers() {
        var result = IntStream.range(1,11).sum();
        System.out.println("\nSum of first 10 natural numbers: "+result);
    }

    public void findStringsWhichStartWithNumber() {
        List<String> listOfStrings = Arrays.asList(
                "XYYZ", "1XZXZ", "4AAA", "BBB", "2CCC"
        );
        var result = listOfStrings.stream()
                .filter(item -> Character.isDigit(item.charAt(0)))
                .toList();

        System.out.println("\nStrings which start with a number: "+result);
    }

    public void findDuplicatesInArray() {
        Set<Integer> set = new HashSet<>();

        List<Integer> duplicatedList = new ArrayList(listOfIntegers);
        duplicatedList.add(3);
        duplicatedList.add(7);
        duplicatedList.add(4);

        var result = duplicatedList.stream()
                .filter(item -> !set.add(item))
                .toList();

        System.out.println("\nDuplicate numbers: "+result);
    }

    public void ageOfPersonInYears() {
        LocalDate bday = LocalDate.of(1985, 01, 23);
        LocalDate today = LocalDate.now();
        System.out.println("\nTotal years: " +ChronoUnit.YEARS.between(bday, today));
    }

    public void fibonacciSeries() {
        System.out.println("\nFibonacci Series: ");
        Stream.iterate(new int[] {0, 1}, f -> new int[] { f[1], f[0]+f[1]})
                .limit(10)
                .map(f -> f[0])
                .forEach(i -> System.out.print(i + " "));
    }


}
