package Model;
public class PricingRule {
    private final int unitPrice;
    private final int specialQuantity;
    private final int specialPrice;

    public PricingRule(int unitPrice, int specialQuantity, int specialPrice) {
        this.unitPrice = unitPrice;
        this.specialQuantity = specialQuantity;
        this.specialPrice = specialPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSpecialQuantity() {
        return specialQuantity;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }
}
