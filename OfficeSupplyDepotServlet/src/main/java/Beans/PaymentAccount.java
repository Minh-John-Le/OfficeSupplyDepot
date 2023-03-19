package Beans;

public class PaymentAccount {
    private int id;
    private int customerId;
    private String name;
    private String expireDate;
    private int cardNumber;

    public PaymentAccount(int id, int customerId, String name, String expireDate, int cardNumber) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.expireDate = expireDate;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}

