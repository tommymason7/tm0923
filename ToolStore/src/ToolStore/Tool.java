package ToolStore;

import javax.annotation.processing.Generated;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tool extends Rentable
{
    public EToolType getToolType()
    {
        return toolType;
    }

    private EToolType toolType = EToolType.NONE;

    private Map<EDayType, Boolean> setDayCharges(EToolType type)
    {
        Map<EDayType, Boolean> dayCharges = new HashMap<EDayType, Boolean>();
        switch (type)
        {
            case NONE:
                break;
            case LADDER:
                dayCharges.put(EDayType.WEEKDAY, true);
                dayCharges.put(EDayType.WEEKEND, true);
                dayCharges.put(EDayType.HOLDIAY, false);
                break;
            case CHAINSAW:
                dayCharges.put(EDayType.WEEKDAY, true);
                dayCharges.put(EDayType.WEEKEND, false);
                dayCharges.put(EDayType.HOLDIAY, true);
                break;
            case JACKHAMMER:
                dayCharges.put(EDayType.WEEKDAY, true);
                dayCharges.put(EDayType.WEEKEND, false);
                dayCharges.put(EDayType.HOLDIAY, false);
                break;
            default:
                System.out.println("Invalid ToolType specified when setting day charges.");
        }
        return dayCharges;
    }

    public Tool()
    {
        this.itemType = EItemType.TOOL;
    }

    public Tool(EToolType toolType, String brand, float pricePerDay, String toolCode)
    {
        super();

        this.itemType = EItemType.TOOL;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.toolType = toolType;
        // Consider forcing uppercase to limit errors but will possibly limit the number of codes that can be added
        this.rentableCode = toolCode;

        this.dayCharges = setDayCharges(this.toolType);
    }
}
