package beans;

import java.math.BigDecimal;

public class OrderDetail {
	private int Id;
	private String OrderCode;
    private int customerID;
    private int shipmethodID;
    private String shipAddress;
    private BigDecimal totalWeight;
    private BigDecimal totalPrice;
    private int paymentCardNumber;
    private String cardName;
    private String expireDate;
    private String deliveryName;
    private String orderDate;
    private String deliveryDate;
    private int totalItem;

    // Getters and Setters
    public int getId()
    {
    	return this.Id;
    }
    
    public void setId(int Id)
    {
    	this.Id = Id;
    }
    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getShipmethodID() {
        return shipmethodID;
    }

    public void setShipmethodID(int shipmethodID) {
        this.shipmethodID = shipmethodID;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public void setPaymentCardNumber(int paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String order_Code) {
		OrderCode = order_Code;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
}
