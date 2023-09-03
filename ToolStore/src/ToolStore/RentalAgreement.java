package ToolStore;

import javax.annotation.processing.Generated;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class RentalAgreement
{
    private float totalCharge = 0.0f;
    private float finalCharge = 0.0f;
    private float discountAmount = 0.0f;
    private LocalDate rentStartDate = null;
    private LocalDate rentEndDate = null;
    private LocalDate checkoutDate = null;

    private int discountPercent = 0;
    private int amountOfDaysRenting = 0;
    private Rentable toolRented = null;

    private int chargedDaysCount = 0;

    public RentalAgreement(Rentable item, LocalDate startDate, int numOfDays, int discountPercent)
    {
        toolRented = item;
        checkoutDate = startDate;

        // The +1 is for the day after the rent which is the start of the rental period.
        rentStartDate = startDate.plusDays(1);
        amountOfDaysRenting = numOfDays;
        this.discountPercent = discountPercent;

        rentEndDate = startDate.plusDays(numOfDays);

        chargedDaysCount = calculateChargeableDays();

        totalCharge = Math.round((chargedDaysCount * toolRented.pricePerDay) * 100.0f) / 100.0f;

        discountAmount = Math.round(((totalCharge * (discountPercent / 100.0f)) * 100.0f)) / 100.0f;

        finalCharge = totalCharge - discountAmount;
    }

    private int calculateChargeableDays()
    {
        boolean shouldCharge = true;
        int dayCount = 0;

        LocalDate date = rentStartDate;

        // Have to add a day to ensure that the last day is processed
        while(!date.equals(rentEndDate.plusDays(1)))
        {
            DayOfWeek day = date.getDayOfWeek();
            switch(day)
            {
                case MONDAY:
                    // Check for Labor Day
                    // Min Day is 1 and Max is 7 for Labor Day
                    if((date.getMonth().equals(Month.SEPTEMBER) && date.getDayOfMonth() <= 7))
                    {
                        shouldCharge = toolRented.dayCharges.get(EDayType.HOLDIAY);
                        break;
                    }

                    // Check for July 4th on the previous day
                    if(date.equals(LocalDate.of(date.getYear(), 7, 5)))
                    {
                        shouldCharge = toolRented.dayCharges.get(EDayType.HOLDIAY);
                        break;
                    }

                case TUESDAY:
                case WEDNESDAY:
                case THURSDAY:
                case FRIDAY:
                    // Check for July 4th on the next day
                    if(date.equals(LocalDate.of(date.getYear(), 7, 3)) && day.equals(DayOfWeek.FRIDAY))
                    {
                        shouldCharge = toolRented.dayCharges.get(EDayType.HOLDIAY);
                    }
                    else
                    {
                        shouldCharge = toolRented.dayCharges.get(EDayType.WEEKDAY);
                    }
                    break;
                case SATURDAY:
                case SUNDAY:
                    shouldCharge = toolRented.dayCharges.get(EDayType.WEEKEND);
                    break;
            }

            if(shouldCharge)
            {
                dayCount++;
            }

            date = date.plusDays(1);
        }

        return dayCount;
    }

    public void printAgreement()
    {
        try
        {
            Tool tool = (Tool) toolRented;

            System.out.println("Tool code: " + tool.rentableCode);
            System.out.println("Tool type: " + tool.getToolType());
            System.out.println("Tool brand: " + tool.brand);
            System.out.println("Rental days: " + amountOfDaysRenting);
            System.out.println("Check out date: " + checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
            System.out.println("Rent start date: " + rentStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
            System.out.println("Due date: " + rentEndDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
            System.out.println("Daily rental charge: $" + String.format("%.2f", tool.pricePerDay));
            System.out.println("Charge days: " + chargedDaysCount);
            System.out.println("Pre-discount charge: $" + String.format("%.2f", totalCharge));
            System.out.println("Discount percent: " + discountPercent + "%");
            System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
            System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
        }
        catch (java.lang.ClassCastException e)
        {
            System.out.println("Can't cast " + toolRented.getClass().toString() + " to Tool");
        }
    }
    public float getTotalCharge() {
        return totalCharge;
    }

    public float getFinalCharge() {
        return finalCharge;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public LocalDate getRentEndDate() {
        return rentEndDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getAmountOfDaysRenting() {
        return amountOfDaysRenting;
    }

    public Rentable getToolRented() {
        return toolRented;
    }

    public int getChargedDaysCount() {
        return chargedDaysCount;
    }

}
