import java.util.*;

// Class to represent an individual Stock
class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

// Class to manage the user's Portfolio
class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        String sym = stock.getSymbol();
        if (holdings.getOrDefault(sym, 0) >= quantity) {
            balance += stock.getPrice() * quantity;
            holdings.put(sym, holdings.get(sym) - quantity);
            System.out.println("Sold " + quantity + " shares of " + sym);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Your Portfolio ---");
        System.out.println("Cash Balance: $" + String.format("%.2f", balance));
        holdings.forEach((sym, qty) -> {
            if (qty > 0) {
                double currentVal = market.get(sym).getPrice() * qty;
                System.out.println(sym + ": " + qty + " shares (Value: $" + String.format("%.2f", currentVal) + ")");
            }
        });
    }
}

public class TradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Portfolio myPortfolio = new Portfolio(10000.00); // Start with $10k
        Map<String, Stock> market = new HashMap<>();

        // Initialize market with some stocks
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOGL", new Stock("GOOGL", 2800.00));
        market.put("TSLA", new Stock("TSLA", 700.00));

        while (true) {
            System.out.println("\n1. Market Data | 2. Buy | 3. Sell | 4. Portfolio | 5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 5) break;

            switch (choice) {
                case 1:
                    System.out.println("\n--- Market Prices ---");
                    market.values().forEach(s -> System.out.println(s.getSymbol() + ": $" + s.getPrice()));
                    break;
                case 2:
                    System.out.print("Enter Symbol to Buy: ");
                    String bSym = sc.next().toUpperCase();
                    if (market.containsKey(bSym)) {
                        System.out.print("Quantity: ");
                        myPortfolio.buyStock(market.get(bSym), sc.nextInt());
                    } else { System.out.println("Stock not found."); }
                    break;
                case 3:
                    System.out.print("Enter Symbol to Sell: ");
                    String sSym = sc.next().toUpperCase();
                    if (market.containsKey(sSym)) {
                        System.out.print("Quantity: ");
                        myPortfolio.sellStock(market.get(sSym), sc.nextInt());
                    }
                    break;
                case 4:
                    myPortfolio.displayPortfolio(market);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}