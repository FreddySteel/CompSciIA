public class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String cutomername, String phoneNumber) {
        this.name = cutomername;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}