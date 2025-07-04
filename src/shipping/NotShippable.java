package shipping;

public class NotShippable implements ShippingPolicy{
    @Override
    public boolean isShippable() {
        return false;
    }
}
