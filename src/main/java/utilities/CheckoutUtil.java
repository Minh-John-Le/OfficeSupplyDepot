package utilities;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import beans.CartItem;

public class CheckoutUtil {
	private BigDecimal freeShippingPrice = new BigDecimal(100);
	private BigDecimal droneWeightLimit = new BigDecimal(15);
	
	public String getAvailableShippingMethod(List<CartItem> cartItemList)
	{
		BigDecimal  weight = getWeight(cartItemList);
		BigDecimal subtotal = getSubtotal(cartItemList);
		return getAvailableShippingMethodHelper(subtotal, weight);
	}
	
	public  BigDecimal getWeight(List<CartItem> cartItemList)
	{
		BigDecimal weight = new BigDecimal(0);
		
		for (int i = 0; i < cartItemList.size(); i++)
		{
			CartItem currentCartItem = cartItemList.get(i);
			BigDecimal quantity = new BigDecimal(currentCartItem.getQuantity());
			BigDecimal subWeight = quantity.multiply(currentCartItem.getProduct().getWeight());
			weight = weight.add(subWeight);
		}
		return weight;
	}
	
	public  BigDecimal getSubtotal(List<CartItem> cartItemList)
	{
		BigDecimal subtotal = new BigDecimal(0);
		
		for (int i = 0; i < cartItemList.size(); i++)
		{
			CartItem currentCartItem = cartItemList.get(i);
			BigDecimal quantity = new BigDecimal(currentCartItem.getQuantity());
			BigDecimal price = quantity.multiply(currentCartItem.getProduct().getPrice());
			subtotal = subtotal.add(price);
		}
		return subtotal;
	}
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
	private String getAvailableShippingMethodHelper(BigDecimal subtotal, BigDecimal weight)
	{
		String shipMethodListStr = "";
		
		if (subtotal.compareTo(freeShippingPrice) >= 0)
		{
			if(weight.compareTo(droneWeightLimit) <= 0)
			{
				shipMethodListStr += "1,";
			}
			shipMethodListStr += "2,";
			shipMethodListStr += "5,";	
			shipMethodListStr += "6";	
		}
		else
		{
			if(weight.compareTo(droneWeightLimit) <= 0)
			{
				shipMethodListStr += "3,";
			}
			shipMethodListStr += "4,";
			shipMethodListStr += "6";
		}
		
		return shipMethodListStr;
	}

	public int getTotalItem(LinkedList<CartItem> cartItemList) {
		int count = 0;
		
		for (int i = 0; i < cartItemList.size(); i++)
		{
			CartItem currentCartItem = cartItemList.get(i);
			count += currentCartItem.getQuantity();
		}
		return count;
	}
	
	public BigDecimal getTotal(BigDecimal subtotal, BigDecimal shippingCost)
	{
		BigDecimal total = subtotal.add(shippingCost);
		return total;
	}

}
