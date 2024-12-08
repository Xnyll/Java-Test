package Application;
import java.util.Map;
import java.util.Scanner;

import Model.PricingRule;
import Service.CheckoutService;

public class CheckoutApp {
    public static void main(String[] args) {
        // Define pricing rules
        Map<Character, PricingRule> pricingRules = Map.of(
            'A', new PricingRule(50, 3, 130),
            'B', new PricingRule(30, 2, 45),
            'C', new PricingRule(20, 0, 0),
            'D', new PricingRule(15, 0, 0)
        );

        // Create a checkout service
        CheckoutService checkoutService = new CheckoutService(pricingRules);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter items (A, B, C, D). Type 'total' to finish:");

        while (true) {
            System.out.print("Input: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if ("TOTAL".equals(input)) {
                System.out.println("Final Total: " + checkoutService.calculateTotal() + " pence.");
                break;
            } else if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                checkoutService.scanItem(input.charAt(0));
                System.out.println("Running Total: " + checkoutService.calculateTotal() + " pence.");
            } else {
                System.out.println("Invalid input. Please enter a valid item or 'total'.");
            }
        }

        scanner.close();
    }
}
