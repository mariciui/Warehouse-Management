package Model;

public class OrderProd {
   public int idorderprod;
   public int quantity;
   public int id_prod;
   public int id_orders;

    public OrderProd() {

    }

    public OrderProd(int idorderprod, int quantity, int id_prod, int id_orders) {
        this.idorderprod = idorderprod;
        this.quantity = quantity;
        this.id_prod = id_prod;
        this.id_orders = id_orders;
    }

    public int getIdorderprod() {
        return idorderprod;
    }

    public void setIdorderprod(int idorderprod) {
        this.idorderprod = idorderprod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_orders() {
        return id_orders;
    }

    public void setId_orders(int id_orders) {
        this.id_orders = id_orders;
    }
}
