package xyz.venuprasath;

import xyz.venuprasath.streams.basics.StreamCreation;
import xyz.venuprasath.streams.intermediate.IntermediateOperators;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!\n\n\n");

        // creation of stream
        StreamCreation creation = new StreamCreation();
        creation.fromCollection();
        creation.usingDotOf();
        creation.usingGenerate();
        creation.fromArray();
        creation.usingBuilder();

        //using intermediate operators
        IntermediateOperators intermediate = new IntermediateOperators();
        intermediate.usingFilters();
        intermediate.usingMap();
        intermediate.usingFlatMap();
        intermediate.sorting();
        intermediate.findindDistinct();
        intermediate.findingPeek();
        intermediate.usingSkipAndLimit();
        intermediate.usingGroupingBy();
    }
}