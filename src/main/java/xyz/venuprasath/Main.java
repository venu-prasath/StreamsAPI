package xyz.venuprasath;

import xyz.venuprasath.streams.basics.StreamCreation;
import xyz.venuprasath.streams.intermediate.*;

import javax.sql.rowset.spi.TransactionalWriter;

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

        intermediate.findTotalSalaryPerDept();
        intermediate.findingAvgSalaryPerDept();
        intermediate.highestPaidEmployeesPerDept();

        TransactionAnalysis ta = new TransactionAnalysis();
        ta.findTransactionsLessThan(100.0);
        ta.groupAndSortTransactions();
        ta.findTopNTransactions(3);


        ProductProcessing pp = new ProductProcessing();
        pp.productsWithPriceAbove4000();
        pp.budgetFriendlyProducts();


        StudentPerformance sp = new StudentPerformance();
        sp.averageGradePerStudent();

        SalesProcessing salesProcessing = new SalesProcessing();
        salesProcessing.findMonthWithHighestSales();
    }
}