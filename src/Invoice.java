public class Invoice {
    private String customer;
    private Integer phoneNumber;

    public Invoice(String customer, Integer phoneNumber) {
        this.customer = customer;
        this.phoneNumber = phoneNumber;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    //Get number order and of what product
    //Multiply price by number ordered
    //store as arraylist and store in allinvoices class



}
