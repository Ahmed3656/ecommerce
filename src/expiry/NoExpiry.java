package expiry;

public class NoExpiry implements ExpiryPolicy {
    @Override
    public boolean expires() {
        return false;
    }
}
