package beans;

public class OrderPageFilter {
    private String orderNumber;
    private String fromOrderDay;
    private String toOrderDay;
    private String fromDeliveryDay;
    private String toDeliveryDay;
    private String sortBy;
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getFromOrderDay() {
        return fromOrderDay;
    }
    
    public void setFromOrderDay(String fromOrderDay) {
        this.fromOrderDay = fromOrderDay;
    }
    
    public String getToOrderDay() {
        return toOrderDay;
    }
    
    public void setToOrderDay(String toOrderDay) {
        this.toOrderDay = toOrderDay;
    }
    
    public String getFromDeliveryDay() {
        return fromDeliveryDay;
    }
    
    public void setFromDeliveryDay(String fromDeliveryDay) {
        this.fromDeliveryDay = fromDeliveryDay;
    }
    
    public String getToDeliveryDay() {
        return toDeliveryDay;
    }
    
    public void setToDeliveryDay(String toDeliveryDay) {
        this.toDeliveryDay = toDeliveryDay;
    }
    
    public String getSortBy() {
        return sortBy;
    }
    
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
