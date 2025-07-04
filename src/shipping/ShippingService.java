package shipping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShippingService {
    public static void shipItems(List<ShippableItem> items) {
        System.out.println("** Shipment notice **");

        Map<String, List<ShippableItem>> grouped = items.stream().collect(Collectors.groupingBy(ShippableItem::getName));

        double totalWeight = 0;

        for (Map.Entry<String, List<ShippableItem>> entry : grouped.entrySet()) {
            int count = entry.getValue().size();
            double weightPerItem = entry.getValue().get(0).getWeight();
            System.out.printf("%dx %s %.0fg%n", count, entry.getKey(), weightPerItem * 1000);
            totalWeight += weightPerItem * count;
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
