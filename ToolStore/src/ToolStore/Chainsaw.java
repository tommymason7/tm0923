package ToolStore;

public class Chainsaw extends Tool
{
    public Chainsaw(String brand, float pricePerDay)
    {
        super();
        this.dayCharges.put(EDayType.WEEKDAY, true);
        this.dayCharges.put(EDayType.WEEKEND, false);
        this.dayCharges.put(EDayType.HOLDIAY, true);

        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }
}
