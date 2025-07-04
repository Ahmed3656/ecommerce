import expiry.Expires;
import expiry.ExpiryPolicy;
import shipping.Shippable;
import shipping.ShippableItem;
import shipping.ShippingPolicy;

public class Product implements ShippableItem {
    private String name;
    private Double price;
    private Integer quantity;

    private ExpiryPolicy expiryPolicy;
    private ShippingPolicy shippingPolicy;

    public Product(String name, Double price, Integer quantity, ExpiryPolicy expiryPolicy, ShippingPolicy shippingPolicy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryPolicy = expiryPolicy;
        this.shippingPolicy = shippingPolicy;
    }

    public boolean isShippable() {
        return shippingPolicy instanceof Shippable;
    }

    public boolean expires() {
        return expiryPolicy.expires();
    }

    public boolean isExpired() {
        if (expiryPolicy instanceof Expires) {
            return ((Expires) expiryPolicy).isExpired();
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        if (shippingPolicy instanceof Shippable) {
            return ((Shippable) shippingPolicy).getWeight();
        }
        return 0.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
