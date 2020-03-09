package Model;

public class Orders {

    private int id;
    private int price;
    private int customerID;

    public Orders() {

    }

    public Orders(int id, int price, int customerID) {
        this.id = id;
        this.price = price;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
