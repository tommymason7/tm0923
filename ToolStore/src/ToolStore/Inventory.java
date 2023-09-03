package ToolStore;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Inventory
{
    private Set<Rentable> items = new HashSet<Rentable>();

    public void addRentableItem(Rentable item)
    {
        items.add(item);
    }

    public Set<Rentable> search(EItemType type)
    {
        Set<Rentable> foundItems = null;

        for(Rentable i : items)
        {
            if(i.itemType == type)
                foundItems.add(i);
        }

        return foundItems;
    }

    public Set<Rentable> search(EToolType type)
    {
        Set<Rentable> foundItems = null;
        Set<Rentable> searchResults = search(EItemType.TOOL);

        for(Rentable i : searchResults)
        {
            Tool item = (Tool)i;
            if(item != null && item.getToolType() == type)
                foundItems.add(i);
        }

        return foundItems;
    }

    public Rentable search(String code)
    {
        for(Rentable i : items)
        {
            if(i.rentableCode == code)
                return i;
        }

        return null;
    }
}
