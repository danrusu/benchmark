package test1streams;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// from Modern Java in Action - 2018 - Raoul-Gabriel Urma, pag 117
public class StreamTest {

    private static List<Transaction> transactions;

    @BeforeClass
    public static void beforeClass() {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //transactions = Collections.emptyList();

        /* Solve the following tasks in separate methods and test them in the main method.
        0. Print all transactions, each one on a new row
        1. Find all transactions in the year 2011 and sort them by value (small to high).
        2. What are all the unique cities where the traders work?
        3. Find all traders from Cambridge and sort them by name.
        4. Return a string of all traders’ names sorted alphabetically.
        5. Are any traders based in Milan?
        6. Print the values of all transactions from the traders living in Cambridge.
        7. What’s the highest value of all the transactions?
        8. Find the transaction with the smallest value.
        */
    }

    @Test
    public void test0() {
        System.out.println("0. Print all transactions, each one on a new row");
        transactions.forEach(System.out::println);
    }

    @Test
    public void test1() {
        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high).");
        List<Transaction> transactionsFrom2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        transactionsFrom2011.forEach(System.out::println);
    }

    @Test
    public void test2() {
        System.out.println("2. What are all the unique cities where the traders work?");
        List<String> uniqueTradersCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        uniqueTradersCities.forEach(System.out::println);
    }

    @Test
    public void test3() {
        System.out.println("3. Find all traders from Cambridge and sort them by name.");
        List<Trader> tradersFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader())
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        tradersFromCambridge.forEach(System.out::println);
    }

    @Test
    public void test4() {
        System.out.println("4. Return a string of all traders’ names sorted alphabetically.");
        String tradersNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(joining(", "));

        System.out.println(tradersNames);
    }

    @Test
    public void test5() {
        System.out.println("5. Are any traders based in Milan?");
        boolean areThereTradersFromMilan = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(name -> name.equals("Milan"));

        System.out.println(areThereTradersFromMilan);
    }

    @Test
    public void test6() {
        System.out.println("6. Print the values of all transactions from the traders living in Cambridge sorted in descending order.");
        Stream<Integer> cambridgeTransactionsValues = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted(reverseOrder());

        cambridgeTransactionsValues.forEach(System.out::println);
    }

    @Test
    public void test7() {
        System.out.println("7. What’s the highest value of all the transactions?");
        OptionalInt highestTransactionValue = transactions.stream()
                //.map(Transaction::getValue)
                //.reduce(Integer::max)
                .mapToInt(Transaction::getValue)
                .max();

        System.out.println(highestTransactionValue.orElse(0));
    }

    @Test
    public void test8() {
        System.out.println("8. Find the transaction with the smallest value.");
        Optional<Transaction> transactionWithTheSmallestValue = transactions.stream()
                .min(comparing(Transaction::getValue));

        transactionWithTheSmallestValue.ifPresent(System.out::println);
    }

}
