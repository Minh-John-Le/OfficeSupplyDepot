package Beans;

public class BankAccount {
    private int id;
    private int storeId;
    private String name;
    private String expireDate;
    private int bankAccountNumber;
    
    public BankAccount(int id, int storeId, String name, String expireDate, int bankAccountNumber) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.expireDate = expireDate;
        this.bankAccountNumber = bankAccountNumber;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getStoreId() {
        return storeId;
    }
    
    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
    
    public int getBankAccountNumber() {
        return bankAccountNumber;
    }
    
    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
