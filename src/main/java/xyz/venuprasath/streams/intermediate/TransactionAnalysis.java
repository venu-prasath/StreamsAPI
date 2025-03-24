package xyz.venuprasath.streams.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Transaction(double amount, String type) {}

public class TransactionAnalysis {

    List<Transaction> transactions;

    public TransactionAnalysis() {
        transactions = Arrays.asList(
                new Transaction(1500, "Credit"),
                new Transaction(50, "Debit"),
                new Transaction(2000, "Credit"),
                new Transaction(75, "Debit"),
                new Transaction(3000, "Credit")
        );
    }

    public void findTransactionsLessThan(Double amount) {
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.amount() < amount)
                .toList();

        System.out.println("\nFiltered Transactions: " + filtered);
    }

    //group transactions by type and sort within groups
    public void groupAndSortTransactions() {
        System.out.println("\nGrouped ans Sorted Transactions: ");
        Map<String, List<Transaction>> map = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::type));

        map.forEach((type, txnList) -> {
            txnList.sort(Comparator.comparing(Transaction::amount).reversed());
        });

        map.forEach((t, txnList) -> {
            System.out.println(t + ": " + txnList);
        });
    }

    //Top N transactions overall
    public void findTopNTransactions(int N) {
        System.out.println("\nTop N transactions: ");
        List<Transaction> txns = transactions.stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(N)
                .toList();

        System.out.println(txns);
    }
}
