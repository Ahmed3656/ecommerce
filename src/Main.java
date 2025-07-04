import expiry.Expires;
import expiry.NoExpiry;
import shipping.NotShippable;
import shipping.Shippable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Customer ahmed = new Customer("Ahmed", 600.0);
        Customer amr = new Customer("Amr", 200.0);

        Map<String, Product> catalog = new HashMap<>();
        Product cheese = new Product("Cheese", 100.0, 2, new Expires(LocalDate.now().plusDays(3)), new Shippable(0.2));
        Product biscuits = new Product("Biscuits", 150.0, 1, new Expires(LocalDate.now().plusDays(2)), new Shippable(0.7));
        Product tv = new Product("TV", 300.0, 1, new NoExpiry(), new NotShippable());
        Product phone = new Product("Phone", 250.0, 1, new NoExpiry(), new Shippable(1.2));

        catalog.put("Cheese", cheese);
        catalog.put("Biscuits", biscuits);
        catalog.put("TV", tv);
        catalog.put("Phone", phone);

        // valid checkout
        Cart cart = new Cart(catalog);
        cart.add("Cheese", 2);
        cart.add("Biscuits", 1);
        try {
            cart.checkout(ahmed);
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }

        // -------------------------
        // not enough stock
//        Cart cart = new Cart(catalog);
//        cart.add("Biscuits", 2); // only 1 in stock
//        try {
//            cart.checkout(ahmed);
//        } catch (Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }

        // -------------------------
        // product expired
//        cheese = new Product("Cheese", 100.0, 2, new Expires(LocalDate.now().minusDays(1)), new Shippable(0.2));
//        catalog.put("Cheese", cheese);
//        Cart cart = new Cart(catalog);
//        cart.add("Cheese", 1);
//        try {
//            cart.checkout(ahmed);
//        } catch (Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }

        // -------------------------
        // insufficient balance
//        Cart cart = new Cart(catalog);
//        cart.add("Phone", 1);
//        try {
//            cart.checkout(amr);
//        } catch (Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }

        // -------------------------
        // product not in catalog
//        Cart cart = new Cart(catalog);
//        cart.add("Milk", 1);
//        try {
//            cart.checkout(ahmed);
//        } catch (Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }

        // -------------------------
        // shippable and non-shippable mix
//        Cart cart = new Cart(catalog);
//        cart.add("TV", 1);       // not shippable
//        cart.add("Phone", 1);    // shippable (1.2kg)
//        try {
//            cart.checkout(ahmed);
//        } catch (Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }
    }
}
