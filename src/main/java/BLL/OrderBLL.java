package BLL;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.OrderProdDAO;
import DAO.ProductDAO;
import Model.OrderProd;
import Model.Orders;
import Model.Product;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class OrderBLL {
int newID = 0;
int orderProductId = 0;
//
    public List<Orders> getAllOrders()
    {
        OrderDAO o =new OrderDAO();
        return o.findAll();
    }

    public int insertOrders(Orders orders) {

        return OrderDAO.insert(orders);
    }
    public void deleteOrders(int id)
    {
        OrderDAO o = new OrderDAO();
        o.deleteById(id);
    }
    public void insertOrder(int cId, List<Product> products) throws FileNotFoundException, UnsupportedEncodingException, InsufficientQuantityException {
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();

        Product p1;
        Product aux;
        Iterator<Product> prodId=products.iterator();
        int total=0;
        while(prodId.hasNext())
        {
            p1 = prodId.next();
            aux = productDAO.findById(p1.getId());

            if(aux.getQuantity() - p1.getQuantity() < 1)
                throw new InsufficientQuantityException();
            total+=p1.getQuantity()*aux.getPrice();
        }
    createOrder(cId,products,total);
    }
    private void createOrder(int id, List<Product> products,int price ) throws FileNotFoundException, UnsupportedEncodingException {
        OrderDAO o = new OrderDAO();
        OrderProd orderProd;
        Product product;
        ProductDAO productDAO = new ProductDAO();


        List<Orders> orders = o.findAll();
        newID ++;
        o.insert(new Orders(newID,price,id));

        OrderProdDAO orderProdDAO = new OrderProdDAO();

        File file =new File("bills" + newID + ".txt");
        PrintWriter writer = new PrintWriter(file,"UTF-8");
        writer.println("Client: " + id);

        Product product1,product2;
        Iterator<Product> productIterator=products.iterator();

        while(productIterator.hasNext())
        {
            product=productIterator.next();
            orderProductId++;
            orderProd=new OrderProd(orderProductId,newID,product.getId(),product.getQuantity());
            writer.println("Product id: " + product.getId() + " quantity : " + product.getQuantity());
            orderProdDAO.insert( orderProd);
        }
        writer.println("Total price: " + price);
        writer.close();

        Iterator<Product> it=products.iterator();
        while(it.hasNext())
        {
            product1=it.next();
            product2= productDAO.findById(product1.getId());
            product2.setQuantity(product2.getQuantity()-product1.getQuantity());
            productDAO.update(product2,product2.getId());
        }

    }

}
