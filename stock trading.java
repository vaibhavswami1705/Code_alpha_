import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleStockPlatform {

    // Internal class to manage User Portfolio
    static class Portfolio {
        private BigDecimal balance;
        private Map<String, Integer> holdings = new HashMap<>();

        public Portfolio(double initialBalance) {
            this.balance = BigDecimal.valueOf(initialBalance).setScale(2, RoundingMode.HALF_UP);
        }

        public void buyStock(String symbol, BigDecimal price, int quantity) {
            BigDecimal totalCost = price.multiply(BigDecimal.valueOf(quantity));
            
            if (balance.compareTo(totalCost) >= 0) {
                balance = balance.subtract(totalCost);
                holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
                System.out.printf("SUCCESS: Bought %d shares of %s at $%s%n", quantity, symbol, price);
            } else {
                System.out.println("ERROR: Insufficient funds.");
            }
        }

        public void showStatus() {
            System.out.println("\n--- ACCOUNT SUMMARY ---");
            System.out.println("Cash Balance: $" + balance);
            System.out.println("Holdings: " + holdings);
            System.out.println("-----------------------\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio myUser = new Portfolio(5000.00); // Start with $5,000
        
        // Mock Market Data
        Map<String, BigDecimal> marketPrices = new HashMap<>();
        marketPrices.put("AAPL", new BigDecimal("150.25"));
        marketPrices.put("TSLA", new BigDecimal("700.50"));
        marketPrices.put("GOOG", new BigDecimal("2800.75"));

        System.out.println("Welcome to the Java Trade Sim");
        
        while (true) {
            myUser.showStatus();
            System.out.println("Available Stocks: " + marketPrices);
            System.out.print("Enter Symbol to buy (or 'exit'): ");
            String symbol = scanner.nextLine().toUpperCase();

            if (symbol.equals("EXIT")) break;

            if (marketPrices.containsKey(symbol)) {
                System.out.print("Enter quantity: ");
                try {
                    int qty = Integer.parseInt(scanner.nextLine());
                    myUser.buyStock(symbol, marketPrices.get(symbol), qty);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity.");
                }
            } else {
                System.out.println("Stock not found.");
            }
        }
        
        System.out.println("Session Ended.");
        scanner.close();
    }
}
