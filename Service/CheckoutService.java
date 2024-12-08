package Service;
import java.util.HashMap;
import java.util.Map;
import Model.PricingRule;

public class CheckoutService {
    private final Map<Character, PricingRule> pricingRules;
    private final Map<Character, Integer> itemCounts = new HashMap<>();

    public CheckoutService(Map<Character, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scanItem(char item) {
        PricingRule rule = pricingRules.get(item);
        if (rule == null) {
            // If the item is not found in the rules, ignore it
            System.out.println("Item '" + item + "' is not valid and will be ignored.");
            return;
        }
        itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
    }
    

    public int calculateTotal() {
        int total = 0;
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char item = entry.getKey();
            int quantity = entry.getValue();
            PricingRule rule = pricingRules.get(item);

            if (rule.getSpecialQuantity() > 0 && quantity >= rule.getSpecialQuantity()) {
                total += (quantity / rule.getSpecialQuantity()) * rule.getSpecialPrice()
                        + (quantity % rule.getSpecialQuantity()) * rule.getUnitPrice();
            } else {
                total += quantity * rule.getUnitPrice();
            }
        }
        return total;
    }
}
