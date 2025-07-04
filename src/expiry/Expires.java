package expiry;

import java.time.LocalDate;

public class Expires implements ExpiryPolicy {
    private final LocalDate expiryDate;

    public Expires(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean expires() {
        return true;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
