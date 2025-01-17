import org.benek.streamspractice.Trader;
import org.benek.streamspractice.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import static java.util.Comparator.comparing;

void main() {
    List<Transaction> transactions = getTransactions();

    // 1. Encuentra todas las transacciones del 2011 y ordenalas por valor (de menor a mayor)
    List<Transaction> transactions2011 =
            transactions.stream()
                    .filter(transaction -> transaction.getYear() == 2011)
                    .sorted(comparing(Transaction::getValue))
                    .toList();
    transactions2011.forEach(System.out::println);

    // 2. ¿Cuales son todas las ciudades (unicas) donde trabajan los comerciantes?
    List<String> ciudades =
            transactions.stream()
                    .map(transaction -> transaction.getTrader().getCity())
                    .distinct()
                    .toList();
    ciudades.forEach(System.out::println);

    // 3. Encuentra todos los comerciantes de Cambridge y ordenalos por nombre
    List<Trader> tradersCambridge =
            transactions.stream()
                    .map(transaction -> transaction.getTrader()) // obtengo los traders
                    .filter(trader -> trader.getCity().equals("Cambridge")) // filtrar los de Cambridge
                    .distinct()
                    .sorted(comparing(Trader::getName))
                    .toList();
    tradersCambridge.forEach(System.out::println);

    // 4. Devuelve una cadena con los nombres de todos los comerciantes ordenados alfabeticamente
    String tradersString =
            transactions.stream()
                    .map(transaction -> transaction.getTrader().getName())
                    .distinct()
                    .sorted()
                    .reduce("", (n1, n2) -> n1 + n2);
    System.out.println(tradersString);

    // 5. ¿Hay algun comerciante que tenga su sede en Milan?
    boolean hayTraderEnMilan =
            transactions.stream()
                    .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
    System.out.println(hayTraderEnMilan);

    // 6. Imprime todos los valores de las transacciones de los comerciantes que viven en Cambridge
    List<Integer> valoresCambridge =
            transactions.stream()
                    .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                    .map(Transaction::getValue)
                    .toList();
    valoresCambridge.forEach(System.out::println);

    // 7. ¿Cual es el valor más alto de todas las transacciones?
    OptionalInt maxValor =
            transactions.stream()
                    .mapToInt(Transaction::getValue)
                    .max();
    maxValor.ifPresent(System.out::println);

    // 8. Encuentra la transaccion con el menor valor
    transactions.stream()
            .mapToInt(Transaction::getValue)
            .min()
            .ifPresent(System.out::println);
}

private static List<Transaction> getTransactions() {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    return Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
}