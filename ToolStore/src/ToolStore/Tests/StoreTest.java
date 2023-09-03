package ToolStore.Tests;

import ToolStore.RentalAgreement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ToolStore.Store;

import java.time.LocalDate;

public class StoreTest
{
    @Test
    public void checkoutExceptionTest()
    {
        Store store = new Store();

        Exception exception = assertThrows(Exception.class, () -> {
            store.checkout("JAKR", LocalDate.of(2015, 9, 3), 5, 101);
        });

        String expMsg = "The discount percentage has to be a whole number between 0 and 100. Please try again.";

        assertTrue(exception.getMessage().equals(expMsg));

        Exception exception1 = assertThrows(Exception.class, () -> {
           store.checkout("JAKR", LocalDate.of(2020, 8, 1), 0, 0);
        });

        expMsg = "The number of days rented cannot be less then one. Please try again.";
        assertTrue(exception1.getMessage().equals(expMsg));
    }

    @Test
    public void checkoutTest() throws Exception {
        Store store = new Store();

        RentalAgreement agreement = null;

        System.out.println("Starting Checkout 1...");
        agreement = store.checkout("LADW", LocalDate.of(2020, 7, 2), 3, 10);
        agreement.printAgreement();
        assertTrue(agreement.getToolRented().getRentableCode().equals("LADW"));
        assertTrue(agreement.getCheckoutDate().equals(LocalDate.of(2020, 7, 2)));
        assertTrue(agreement.getAmountOfDaysRenting() == 3);
        assertTrue(agreement.getDiscountPercent() == 10);
        assertTrue(agreement.getChargedDaysCount() == 2);
        assertTrue(agreement.getRentStartDate().equals(LocalDate.of(2020, 7, 3)));
        assertTrue(agreement.getRentEndDate().equals(LocalDate.of(2020, 7, 5)));
        assertTrue(agreement.getTotalCharge() == 3.98f);
        assertTrue(agreement.getDiscountAmount() == .4f);
        assertTrue(agreement.getFinalCharge() == 3.58f);

        System.out.println("\nStarting Checkout 2...");
        RentalAgreement agreement1 = store.checkout("CHNS", LocalDate.of(2015, 7, 2), 5, 25);
        agreement1.printAgreement();
        assertTrue(agreement1.getToolRented().getRentableCode().equals("CHNS"));
        assertTrue(agreement1.getCheckoutDate().equals(LocalDate.of(2015, 7, 2)));
        assertTrue(agreement1.getAmountOfDaysRenting() == 5);
        assertTrue(agreement1.getDiscountPercent() == 25);
        assertTrue(agreement1.getChargedDaysCount() == 3);
        assertTrue(agreement1.getRentStartDate().equals(LocalDate.of(2015, 7, 3)));
        assertTrue(agreement1.getRentEndDate().equals(LocalDate.of(2015, 7, 7)));
        assertTrue(agreement1.getTotalCharge() == 4.47f);
        assertTrue(agreement1.getDiscountAmount() == 1.12f);
        assertTrue(agreement1.getFinalCharge() == 3.35f);

        System.out.println("\nStarting Checkout 3...");
        RentalAgreement agreement2 = store.checkout("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
        agreement2.printAgreement();
        assertTrue(agreement2.getToolRented().getRentableCode().equals("JAKD"));
        assertTrue(agreement2.getCheckoutDate().equals(LocalDate.of(2015, 9, 3)));
        assertTrue(agreement2.getAmountOfDaysRenting() == 6);
        assertTrue(agreement2.getDiscountPercent() == 0);
        assertTrue(agreement2.getChargedDaysCount() == 3);
        assertTrue(agreement2.getRentStartDate().equals(LocalDate.of(2015, 9, 4)));
        assertTrue(agreement2.getRentEndDate().equals(LocalDate.of(2015, 9, 9)));
        assertTrue(agreement2.getTotalCharge() == 8.97f);
        assertTrue(agreement2.getDiscountAmount() == 0f);
        assertTrue(agreement2.getFinalCharge() == 8.97f);

        System.out.println("\nStarting Checkout 4...");
        RentalAgreement agreement3 = store.checkout("JAKR", LocalDate.of(2015, 7, 2), 9, 0);
        agreement3.printAgreement();
        assertTrue(agreement3.getToolRented().getRentableCode().equals("JAKR"));
        assertTrue(agreement3.getCheckoutDate().equals(LocalDate.of(2015, 7, 2)));
        assertTrue(agreement3.getAmountOfDaysRenting() == 9);
        assertTrue(agreement3.getDiscountPercent() == 0);
        assertTrue(agreement3.getChargedDaysCount() == 5);
        assertTrue(agreement3.getRentStartDate().equals(LocalDate.of(2015, 7, 3)));
        assertTrue(agreement3.getRentEndDate().equals(LocalDate.of(2015, 7, 11)));
        assertTrue(agreement3.getTotalCharge() == 14.95f);
        assertTrue(agreement3.getDiscountAmount() == 0f);
        assertTrue(agreement3.getFinalCharge() == 14.95f);

        System.out.println("\nStarting Checkout 5...");
        RentalAgreement agreement4 = store.checkout("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
        agreement4.printAgreement();
        assertTrue(agreement4.getToolRented().getRentableCode().equals("JAKR"));
        assertTrue(agreement4.getCheckoutDate().equals(LocalDate.of(2020, 7, 2)));
        assertTrue(agreement4.getAmountOfDaysRenting() == 4);
        assertTrue(agreement4.getDiscountPercent() == 50);
        assertTrue(agreement4.getChargedDaysCount() == 1);
        assertTrue(agreement4.getRentStartDate().equals(LocalDate.of(2020, 7, 3)));
        assertTrue(agreement4.getRentEndDate().equals(LocalDate.of(2020, 7, 6)));
        assertTrue(agreement4.getTotalCharge() == 2.99f);
        assertTrue(agreement4.getDiscountAmount() == 1.5f);
        assertTrue(agreement4.getFinalCharge() == 1.49f);
    }
}