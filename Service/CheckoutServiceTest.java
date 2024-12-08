package Service;

import Model.PricingRule;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @Before
    public void setUp() {
        // Define pricing rules
        Map<Character, PricingRule> pricingRules = Map.of(
            'A', new PricingRule(50, 3, 130),
            'B', new PricingRule(30, 2, 45),
            'C', new PricingRule(20, 0, 0),
            'D', new PricingRule(15, 0, 0)
        );

        // Initialize CheckoutService with pricing rules
        checkoutService = new CheckoutService(pricingRules);
    }

    @Test
    public void testScanSingleItem() {
        checkoutService.scanItem('A');
        assertEquals(50, checkoutService.calculateTotal());
    }

    @Test
    public void testScanMultipleItemsWithoutSpecialPricing() {
        checkoutService.scanItem('C');
        checkoutService.scanItem('D');
        assertEquals(35, checkoutService.calculateTotal());
    }

    @Test
    public void testSpecialPricingForA() {
        checkoutService.scanItem('A');
        checkoutService.scanItem('A');
        checkoutService.scanItem('A'); // Apply special pricing for 3 A's
        assertEquals(130, checkoutService.calculateTotal());
    }

    @Test
    public void testSpecialPricingForB() {
        checkoutService.scanItem('B');
        checkoutService.scanItem('B'); // Apply special pricing for 2 B's
        assertEquals(45, checkoutService.calculateTotal());
    }

    @Test
    public void testMixedItemsWithSpecialPricing() {
        checkoutService.scanItem('A');
        checkoutService.scanItem('A');
        checkoutService.scanItem('A'); // Apply special pricing for 3 A's
        checkoutService.scanItem('B');
        checkoutService.scanItem('B'); // Apply special pricing for 2 B's
        checkoutService.scanItem('C');
        checkoutService.scanItem('D');
        assertEquals(210, checkoutService.calculateTotal());
    }

    @Test
    public void testItemsWithoutSpecialPricing() {
        checkoutService.scanItem('A');
        checkoutService.scanItem('A'); // Only 2 A's, no special pricing applied
        assertEquals(100, checkoutService.calculateTotal());
    }

    @Test
    public void testNoItemsScanned() {
        assertEquals(0, checkoutService.calculateTotal());
    }

    @Test
    public void testInvalidItemDoesNotBreakService() {
        checkoutService.scanItem('Z'); // Z is not in the pricing rules
        assertEquals(0, checkoutService.calculateTotal());
    }
}
