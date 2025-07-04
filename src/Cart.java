import shipping.ShippableItem;
import shipping.ShippingService;

import java.util.*;

public class Cart {
    private Map<Product, Integer> cartItems = new HashMap<>();
    private final Map<String, Product> catalog;

    public Cart(Map<String, Product> catalog) {
        this.catalog = catalog;
    }

    public void add(String name, Integer quantity) {
        if (!catalog.containsKey(name)) {
            throw new IllegalArgumentException("Product '" + name + "' does not exist in catalog.");
        }

        Product product = catalog.get(name);
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for product: " + name);
        }

        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
    }

    public boolean checkout(Customer customer) {
        double subtotal = 0;
        double shipping = 0;
        List<ShippableItem> itemsToShip = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.expires() && product.isExpired()) {
                throw new IllegalArgumentException(product.getName() + " is expired.");
            }

            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }

            subtotal += product.getPrice() * quantity;

            if (product.isShippable()) {
                for (int i = 0; i < quantity; i++) {
                    itemsToShip.add(product);
                    shipping += 15;
                }
            }
        }

        double total = subtotal + shipping;
        if (customer.getBalance() < total) {
            throw new IllegalArgumentException("Customer does not have enough balance.");
        }

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
        }
        customer.setBalance(customer.getBalance() - total);

        if (!itemsToShip.isEmpty()) {
            ShippingService.shipItems(itemsToShip);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n", total);

        cartItems.clear();

        return true;
    }
}
