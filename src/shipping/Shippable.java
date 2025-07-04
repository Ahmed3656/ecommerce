package shipping;

public class Shippable implements ShippingPolicy {
    private final double weight;

    public Shippable(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    public double getWeight() {
        return weight;
    }
}
