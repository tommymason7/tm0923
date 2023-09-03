package ToolStore;

import java.util.Map;

public class Rentable
{
    protected String rentableCode = "";

    // Not as scalable as strings but enum comparison is more reliable than string comparison
    // (i.e. compile time error vs fat finger error that isn't caught by anything but testing)
    protected EItemType itemType = EItemType.NONE;

    protected String brand = "";

    protected float pricePerDay = 0.00f;

    protected Map<EDayType, Boolean> dayCharges;

    protected String serialId = "";

    protected RentalAgreement rentalAgreement = null;

    public String getRentableCode() {
        return rentableCode;
    }

    public EItemType getItemType() {
        return itemType;
    }

    public String getBrand() {
        return brand;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public Map<EDayType, Boolean> getDayCharges() {
        return dayCharges;
    }

    public String getSerialId() {
        return serialId;
    }

    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }
}
