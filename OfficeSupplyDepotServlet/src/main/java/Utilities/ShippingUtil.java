package Utilities;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class ShippingUtil {
	private BigDecimal freeShippingPrice = new BigDecimal(100);
	private BigDecimal droneWeightLimit = new BigDecimal(15);
	/**
	 * Note:
	 * 1 is Drone Free
	 * 2 is Truck Free
	 * 3 is Drone
	 * 4 is Truck
	 * 5 is Truck-Same Day
	 * @param subtotal
	 * @param weight
	 * @return
	 */
	public List<Integer> getAvailableShippingMethod(BigDecimal subtotal, BigDecimal weight)
	{
		List<Integer> shippingMethodLists = new LinkedList<Integer>();
		
		if (subtotal.compareTo(freeShippingPrice) >= 0)
		{
			if(weight.compareTo(droneWeightLimit) <= 0)
			{
				shippingMethodLists.add(1);
			}
			shippingMethodLists.add(2);
			shippingMethodLists.add(5);	
		}
		else
		{
			if(weight.compareTo(droneWeightLimit) <= 0)
			{
				shippingMethodLists.add(3);
			}
			shippingMethodLists.add(4);
		}
		return shippingMethodLists;
	}

}
