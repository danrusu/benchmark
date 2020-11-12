package test1streams;

import java.util.List;

// from Modern Java in Action - 2018 - Raoul-Gabriel Urma, pag 117
public class Test {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /* Solve the following tasks in separate methods and test them in the main method.
        1 Find all transactions in the year 2011 and sort them by value (small to high).
        2 What are all the unique cities where the traders work?
        3 Find all traders from Cambridge and sort them by name.
        4 Return a string of all traders’ names sorted alphabetically.
        5 Are any traders based in Milan?
        6 Print the values of all transactions from the traders living in Cambridge.
        7 What’s the highest value of all the transactions?
        8 Find the transaction with the smallest value.
         */
    }

}
