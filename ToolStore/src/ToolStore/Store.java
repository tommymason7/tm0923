package ToolStore;

import java.time.LocalDate;

public class Store
{
    private Inventory inv = new Inventory();

    public Store()
    {
        // Instantiate inventory
        Tool chainsaw = new Tool(EToolType.CHAINSAW, "Stihl", 1.49f, "CHNS");
        Tool ladder = new Tool(EToolType.LADDER, "Werner", 1.99f, "LADW");
        Tool jackHammer1 = new Tool(EToolType.JACKHAMMER, "DeWalt", 2.99f, "JAKD");
        Tool jackHammer2 = new Tool(EToolType.JACKHAMMER, "Ridgid", 2.99f, "JAKR");

        inv.addRentableItem(chainsaw);
        inv.addRentableItem(ladder);
        inv.addRentableItem(jackHammer1);
        inv.addRentableItem(jackHammer2);
    }

    public RentalAgreement checkout(String code, LocalDate startingDate, int numOfDays, int discountPercent) throws Exception
    {
        // Find tool that is being requested to be rented
        Rentable item = inv.search(code);

        if(item != null)
        {
            if(numOfDays < 1)
            {
                throw new Exception("The number of days rented cannot be less then one. Please try again.");
            }

            if(discountPercent < 0 || discountPercent > 100)
            {
                throw new Exception("The discount percentage has to be a whole number between 0 and 100. Please try again.");
            }

            // Generate Rental Agreement
            return new RentalAgreement(item, startingDate, numOfDays, discountPercent);
        }

        return null;
    }
}
